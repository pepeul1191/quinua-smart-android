package pe.edu.ulima.idic.quinua.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.util.Iterator;

import pe.edu.ulima.idic.quinua.R;
import pe.edu.ulima.idic.quinua.models.Sesion;
import pe.edu.ulima.idic.quinua.utils.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    // Reference of DatabaseHelper class to access its DAOs and other components
    private DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final int[] temp = {0};

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                try{
                    final Dao<Sesion, Integer> sesionDao = getHelper().getTeacherDao();
                    QueryBuilder<Sesion, Integer> queryBuilder = sesionDao.queryBuilder();
                    Where where = queryBuilder.where();
                    where.eq("llave", "autenticado");
                    where.and();
                    where.eq("valor", "true");
                    final PreparedQuery<Sesion> preparedQuery = queryBuilder.prepare();
                    final Iterator<Sesion> sesionIt = sesionDao.query(preparedQuery).iterator();

                    while (sesionIt.hasNext()) {
                        final Sesion s= sesionIt.next();
                        temp[0] = 1;
                    }
                }catch(Exception e){
                    Log.d("TRY1", e.toString());
                }

                if(temp[0] == 0) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }else {
                    Intent applicationIntent = new Intent(MainActivity.this, ApplicationActivity.class);
                    startActivity(applicationIntent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }

    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
