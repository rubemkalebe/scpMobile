package imd.ufrn.br.scpmobile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.ActivityTestCase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import imd.ufrn.br.scpmobile.model.Pessoa;

public class LoginActivity extends AppCompatActivity {

    private EditText email_et;
    private Button login_bt;

    private RequestQueue requestQueue;

    private static final int MAIN_ACTIVITY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_et = (EditText) findViewById(R.id.login_email_et);
        login_bt = (Button) findViewById(R.id.login_bt);
    }

    public void login(View view) {

        if(!validateLogin()) {
            onLoginFailed();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Entrando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = "http://45.55.40.43:8000/scp/pessoa/email/" + email_et.getText().toString();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("OK", "SUCESSO");
                        Pessoa p = new Pessoa();
                        try {
                            p.setId(response.getInt("pessoa_id"));
                            p.setNome(response.getString("nome"));
                            p.setDepartamento(response.getString("departamento"));
                            p.setEmail(response.getString("email"));
                            Log.d("DBG", p.getNome());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();

                        openMain(p);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Login::Error", error.toString());
                        progressDialog.dismiss();
                    }
                }
        );

        getRequestQueue().add(request);
    }

    private boolean validateLogin() {
        String email = email_et.getText().toString();

        if(!email.contains("@") && !(email.length() > 3)) {
            email_et.setError("E-mail inválido");
            return false;
        } else {
            email_et.setError(null);
        }

        return true;
    }

    private void onLoginFailed() {
        login_bt.setEnabled(true);
    }

    private void openMain(Pessoa p) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("idPessoa", p.getId());
        i.putExtra("nome", p.getNome());
        i.putExtra("email", p.getEmail());
        Log.d("DBG", p.getNome());
        startActivityForResult(i, MAIN_ACTIVITY_INTENT);
    }

    private RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
}
