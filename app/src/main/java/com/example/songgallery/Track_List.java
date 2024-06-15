package com.example.songgallery;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class Track_List extends Fragment {


    private int[] tracksList;
    private String[] trackName;
    private String[] trackArtist;
    private int currentTrackIndex = 0;
    private LinearLayout parent;
    private TextView track, songDuration, currentSongDuration;
    private ImageView closeDialog, playMusic;
    private static boolean isPlaying = false;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track__list, container, false);

        parent = view.findViewById(R.id.track_list_layout);
        tracksList = new int[]{R.raw.time_ringtone, R.raw.fairytale_ringtone, R.raw.cut_your_teeth_ringtone, R.raw.into_your_arms_ringtone, R.raw.leave_a_smile_ringtone,
                R.raw.ocean_drive_ringtone, R.raw.lost_without_you_ringtone, R.raw.street_party_ringtone, R.raw.bones_ringtone, R.raw.echo_ringtone, R.raw.flashbang_dance_ringtone};

        trackName = getResources().getStringArray(R.array.track_name);
        trackArtist = getResources().getStringArray(R.array.track_artist);
        createTrackLayout();

        return view;
    }

    private void createTrackLayout() {
        for (int i = 1; i <= 11; i++) {
            track = new TextView(getContext());
            track.setId(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1,130);
            params.setMargins(0,30,0,0);
            track.setLayoutParams(params);
            track.setText("רצועה");
            track.setTextSize(20);
            track.setTextColor(Color.BLACK);
            track.setGravity(Gravity.CENTER);
            track.setBackground(getResources().getDrawable(R.drawable.album_artist_track_style));

            track.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentTrackIndex < tracksList.length) {
                        createPreviewSongDialog(tracksList[currentTrackIndex], trackName[currentTrackIndex], trackArtist[currentTrackIndex]);
                        currentTrackIndex++;
                    }
                }
            });
            parent.addView(track);
        }
    }

    public void createPreviewSongDialog(int track, String trackName, String trackArtist)
    {
        mediaPlayer = MediaPlayer.create(getContext(), track);

        Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.preview_song_dialog);
        playMusic = dialog.findViewById(R.id.play);
        playMusic.setBackground(getResources().getDrawable(R.drawable.play_circle));

        closeDialog = dialog.findViewById(R.id.close);
        seekBar = dialog.findViewById(R.id.seekBar);

        songDuration = dialog.findViewById(R.id.song_duration);
        songDuration.setText(convertMillisToMinutesAndSeconds(mediaPlayer.getDuration()));
        currentSongDuration = dialog.findViewById(R.id.current_song_duration);

        TextView tvTrackName = dialog.findViewById(R.id.track_name);
        tvTrackName.setText(trackName);
        TextView tvTrackArtist = dialog.findViewById(R.id.track_artist);
        tvTrackArtist.setText(trackArtist);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying) {
                    PlayMedia();
                    playMusic.setBackground(getResources().getDrawable(R.drawable.pause_circle));
                } else {
                    pauseMedia();
                }
            }
        });
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopMedia();
                dialog.dismiss();
            }
        });



        dialog.show();
    }

    private void PlayMedia() {
        seekBar.setMax(mediaPlayer.getDuration());

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                handler.removeCallbacks(updateSeekBar);
                isPlaying = false;
                seekBar.setProgress(0);
                playMusic.setClickable(true);
                playMusic.setBackground(getResources().getDrawable(R.drawable.play_circle));
                currentSongDuration.setText(convertMillisToMinutesAndSeconds(0));
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacks(updateSeekBar);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.postDelayed(updateSeekBar, 1000);
            }
        });

        mediaPlayer.start();
        isPlaying = true;
        handler.postDelayed(updateSeekBar, 1000);
    }



    private void pauseMedia() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playMusic.setBackground(getResources().getDrawable(R.drawable.play_circle));
            isPlaying = false;
            handler.removeCallbacks(updateSeekBar);
        }
    }

    private void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            playMusic.setBackground(getResources().getDrawable(R.drawable.play_circle));
            isPlaying = false;
            handler.removeCallbacks(updateSeekBar);
        }
    }

    @SuppressLint("DefaultLocale")
    public String convertMillisToMinutesAndSeconds(int millis) {
        int seconds = (millis / 1000) % 60;
        int minutes = (millis / (1000 * 60)) % 60;

        // Format the string to always have two digits for seconds
        return String.format("%02d:%02d", minutes, seconds);
    }


    private final Runnable updateSeekBar = new Runnable() {
        @Override
        public void run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            currentSongDuration.setText(convertMillisToMinutesAndSeconds(mediaPlayer.getCurrentPosition()+1000));
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        stopMedia();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMedia();
    }

}