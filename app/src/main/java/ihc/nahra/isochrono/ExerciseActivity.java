package ihc.nahra.isochrono;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseActivity extends AppCompatActivity {

    private Vibrator v;
    private TextView currentSet;
    private TextView currentRep;
    private TextView timerState;
    private TextView timer;

    private MediaPlayer repSound;
    private MediaPlayer setIntervalSound;
    private MediaPlayer exerciseEndSound;

    private int n_set;
    private int i_set;
    private int n_rep;
    private int t_rep;
    private int i_rep;

    private int set;
    private int rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        currentSet = (TextView) findViewById(R.id.currentSetText);
        currentRep = (TextView) findViewById(R.id.currentRepText);
        timerState = (TextView) findViewById(R.id.timerState);
        timerState.setText(R.string.exercise);

        timer = (TextView) findViewById(R.id.timer);

        repSound = MediaPlayer.create(this, R.raw.one_beep);
        setIntervalSound = MediaPlayer.create(this, R.raw.two_beep);
        exerciseEndSound = MediaPlayer.create(this, R.raw.three_beep);

        n_set = Integer.parseInt(intent.getStringExtra(MainActivity.N_SET));
        i_set = Integer.parseInt(intent.getStringExtra(MainActivity.I_SET));
        n_rep = Integer.parseInt(intent.getStringExtra(MainActivity.N_REP));
        t_rep = Integer.parseInt(intent.getStringExtra(MainActivity.T_REP));
        i_rep = Integer.parseInt(intent.getStringExtra(MainActivity.I_REP));

        set = 1;
        rep = 1;

        exercise();
    }

    private void exercise() {
        if (set <= n_set) {
            if (rep <= n_rep) {
                currentSet.setText(String.format("%d/%d", set, n_set));
                currentRep.setText(String.format("%d/%d", rep, n_rep));
                timerState.setText(R.string.exercise);
                repSound.start();
                v.vibrate(150);
                repTime();
            }
        }
    }

    private void repTime() {
        new CountDownTimer(t_rep * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.format("%02d", l / 1000));
            }

            @Override
            public void onFinish() {
                rep++;
                if (rep <= n_rep) {
                    repSound.start();
                    v.vibrate(150);
                    timerState.setText(R.string.interval);
                    repInterval();
                } else {
                    set++;
                    rep = 1;
                    if (set <= n_set) {
                        setIntervalSound.start();
                        long pattern[] = {0, 150, 150, 150};
                        v.vibrate(pattern, -1);
                        timerState.setText(R.string.interval);
                        setInterval();
                    } else {
                        exerciseEndSound.start();
                        long pattern[] = {0, 300, 150, 300, 150, 500};
                        v.vibrate(pattern, -1);
                        timerState.setText(R.string.finished);
                        timer.setText(String.format("%02d", 0));
                    }
                }
            }
        }.start();
    }

    private void repInterval() {
        new CountDownTimer(i_rep * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.format("%02d", l / 1000));
            }

            @Override
            public void onFinish() {
                exercise();
            }
        }.start();
    }

    private void setInterval() {
        new CountDownTimer(i_set * 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.format("%02d", l / 1000));
            }

            @Override
            public void onFinish() {
                exercise();
            }
        }.start();
    }
}
