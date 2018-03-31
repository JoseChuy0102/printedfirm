package josevillarreal.printedfirm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //comentario
        // Holis

        //HOLAAAAA

        findViewById(R.id.btnrec).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnrec:
            {
                Intent intent = new Intent(this, verProductos.class);
                startActivity(intent);
            }break;
        }
    }
}
