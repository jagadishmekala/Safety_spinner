package com.example.voltasassignment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;


public class Dashboard extends AppCompatActivity {
    AlertDialog.Builder builder;
    LinearLayout homeObservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Hide the action bar
        getSupportActionBar().hide();
        //StstusbarColor...!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(Dashboard.this,R.color.DashBoardTextColor));
        }

        ImageView logoutButton = findViewById(R.id.imagelogout_btn_dashboard);

        //Alert Dialogue for logout button
        builder = new AlertDialog.Builder(this);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("ALERT!!")
                        .setMessage("DO YO WANT TO LOGOUT")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Dashboard.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });
        findViewById(R.id.hazrad_layout_dashboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect displayRectangle = new Rect();
                Window window = Dashboard.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this,R.style.CustomAlertDialog);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.hazardlayout, viewGroup, false);
                dialogView.setMinimumWidth((int)(displayRectangle.width() * 0.6f));
                dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.6f));
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                ImageView close_hazard=dialogView.findViewById(R.id.close_hazard);

                close_hazard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }


                });
                AppCompatButton acb =dialogView.findViewById(R.id.pleasereportyourobservation);
                acb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i =new Intent(Dashboard.this, HazradActivity.class);
                        i.putExtra("strType", "hazard");
                        i.putExtra("strSubType", "hazard");
                        i.putExtra("text","HAZARD");
                        i.putExtra("listViewMainHeading", "Hazard");
                        Snackbar.make(v, "Please select observation type", Snackbar.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });
                alertDialog.show();
            }


        });

        findViewById(R.id.nearmisslayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect displayRectangle = new Rect();
                Window window = Dashboard.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this,R.style.CustomAlertDialog);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.nearmisslayout, viewGroup, false);
                dialogView.setMinimumWidth((int)(displayRectangle.width() * 0.5f));
                dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.3f));
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                ImageView close_hazard=dialogView.findViewById(R.id.close_nearmiss_pop);

                close_hazard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }


                });
                AppCompatButton acb =dialogView.findViewById(R.id.pleasereportyourobservation);
                acb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i =new Intent(Dashboard.this, HazradActivity.class);
                        i.putExtra("strType", "Incident");
                        i.putExtra("strSubType", "Near Miss");
                        i.putExtra("text","Near Miss");
                        startActivity(i);
                    }
                });
                alertDialog.show();
            }
        });
        findViewById(R.id.accidentlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect displayRectangle = new Rect();
                Window window = Dashboard.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this,R.style.CustomAlertDialog);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.accidentlayout, viewGroup, false);
                dialogView.setMinimumWidth((int)(displayRectangle.width() * 0.5f));
                dialogView.setMinimumHeight((int)(displayRectangle.height() * 0.3f));
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                ImageView close_hazard=dialogView.findViewById(R.id.close_hazard);

                close_hazard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }


                });
                AppCompatButton acb =dialogView.findViewById(R.id.pleasereportyourobservation);
                acb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i =new Intent(Dashboard.this, HazradActivity.class);
                        i.putExtra("strType", "Incident");
                        i.putExtra("strSubType", "Accident");
                        i.putExtra("text","Accident");
                        startActivity(i);
                    }
                });
                alertDialog.show();
            }
        });
        LinearLayout homeGps;
        homeGps = findViewById(R.id.homeGps);
        homeGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,MapActivity.class);
                startActivity(i);
            }
        });

    }
}

