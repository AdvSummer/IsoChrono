package ihc.nahra.isochrono;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String N_SET = "ihc.nahra.isochrono.N_SET";
    public final static String I_SET = "ihc.nahra.isochrono.I_SET";
    public final static String N_REP = "ihc.nahra.isochrono.N_REP";
    public final static String T_REP = "ihc.nahra.isochrono.T_REP";
    public final static String I_REP = "ihc.nahra.isochrono.I_REP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void beginExercise(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);

        EditText n_setEdit = (EditText)findViewById(R.id.n_setEdit);
        EditText i_setEdit = (EditText)findViewById(R.id.i_setEdit);
        EditText n_repEdit = (EditText)findViewById(R.id.n_repEdit);
        EditText t_repEdit = (EditText)findViewById(R.id.t_repEdit);
        EditText i_repEdit = (EditText)findViewById(R.id.i_repEdit);

        String n_setString = n_setEdit.getText().toString();
        String i_setString = i_setEdit.getText().toString();
        String n_repString = n_repEdit.getText().toString();
        String t_repString = t_repEdit.getText().toString();
        String i_repString = i_repEdit.getText().toString();

        intent.putExtra(N_SET, n_setString);
        intent.putExtra(I_SET, i_setString);
        intent.putExtra(N_REP, n_repString);
        intent.putExtra(T_REP, t_repString);
        intent.putExtra(I_REP, i_repString);

        startActivity(intent);
    }
}
