package imd.ufrn.br.scpmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import imd.ufrn.br.scpmobile.model.Pessoa;
import imd.ufrn.br.scpmobile.model.Projeto;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listaDeProjetos;

    ArrayAdapter<Projeto> adapter;

    private RequestQueue requestQueue;

    private Pessoa p = new Pessoa();

    List<Projeto> projetos = new ArrayList<Projeto>();

    private static final int ENTREGAVEIS_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaDeProjetos = (ListView) findViewById(R.id.projetos_view);

        p.setId(getIntent().getIntExtra("idPessoa", 0));
        p.setNome(getIntent().getStringExtra("nome"));
        p.setEmail(getIntent().getStringExtra("email"));

        adapter = new ArrayAdapter<Projeto>(this,
                android.R.layout.simple_list_item_1);
        listaDeProjetos.setAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getProjetos(p.getId());

        listaDeProjetos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openEntregaveis(position);
            }
        });
    }

    private void openEntregaveis(int index) {
        Intent i = new Intent(this, EntregaveisActivity.class);
        i.putExtra("projeto_id", projetos.get(index).getId());
        startActivityForResult(i, ENTREGAVEIS_INTENT);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<Projeto> getProjetos(Integer idPessoa) {
        String url_ger = "http://45.55.40.43:8000/scp/projeto/gerente/" + String.valueOf(idPessoa);
        String url_pat = "http://45.55.40.43:8000/scp/projeto/patrocinador/" + String.valueOf(idPessoa);

        JsonArrayRequest request_ger = new JsonArrayRequest(Request.Method.GET, url_ger, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response:ger", response.toString());

                        for(int i = 0; i < response.length(); i++) {
                            Projeto p = new Projeto();
                            try {
                                JSONObject pjson = response.getJSONObject(i);
                                p.setId(pjson.getInt("projeto_id"));
                                p.setNome(pjson.getString("nome"));
                                p.setDescricao(pjson.getString("descricao"));

                                projetos.add(p);
                                Log.d("DBG:Size", String.valueOf(projetos.size()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.clear();
                        adapter.addAll(projetos);
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        JsonArrayRequest request_pat = new JsonArrayRequest(Request.Method.GET, url_pat, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        // display response
                        Log.d("Response:ger", response.toString());
                        for(int i = 0; i < response.length(); i++) {
                            Projeto p = new Projeto();
                            try {
                                JSONObject pjson = response.getJSONObject(i);
                                p.setId(pjson.getInt("projeto_id"));
                                p.setNome(pjson.getString("nome"));
                                p.setDescricao(pjson.getString("descricao"));

                                projetos.add(p);
                                Log.d("DBG:Size", String.valueOf(projetos.size()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            adapter.clear();
                            adapter.addAll(projetos);
                            adapter.notifyDataSetChanged();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        getRequestQueue().add(request_ger);
        getRequestQueue().add(request_pat);

        return null;
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
