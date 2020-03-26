package com.example.tugas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    RadioGroup RG1, RG2, RG3, RG4, RGjk;
    RadioButton lk, pr, islam, kristen, katolik, hindu, buddha, konghucu, aliran;
    EditText ETnamaDpn, ETnamaBlkng, ETtmptL, ETdate, ETalmt, ETtlp, ETemail, ETpass;
    Button Btndaftar, Btnkembali, BtnOke, BtnKeluar;

    String Bc, jk, agama, namaDpn, namaBlkng, tempat, tanggal, alamat, telpon, email, password, pass;
    TextView TvnamaDpn, TvnamaBlkng, TvTTL, Tvalamat, Tvjk, Tvagama, Tvtlp, Tvemail;

    int tahun, bulan, hari;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETnamaDpn = findViewById(R.id.ETnamaDpn);
        ETnamaBlkng = findViewById(R.id.ETnamaBlkng);
        ETtmptL = findViewById(R.id.ETtmptL);
        ETdate = findViewById(R.id.ETdate);
        ETalmt = findViewById(R.id.ETalmt);
        ETtlp = findViewById(R.id.ETtlp);
        ETemail = findViewById(R.id.ETemail);
        ETpass = findViewById(R.id.ETpass);

        RGjk = findViewById(R.id.RGjk);
        RG1 = findViewById(R.id.RG1);
        RG2 = findViewById(R.id.RG2);
        RG3 = findViewById(R.id.RG3);
        RG4 = findViewById(R.id.RG4);

        lk = findViewById(R.id.lk);
        pr = findViewById(R.id.pr);
        islam = findViewById(R.id.islam);
        kristen = findViewById(R.id.kristen);
        katolik = findViewById(R.id.katolik);
        hindu = findViewById(R.id.hindu);
        buddha = findViewById(R.id.buddha);
        konghucu = findViewById(R.id.konghucu);
        aliran = findViewById(R.id.aliran);

        Btndaftar = findViewById(R.id.Btndaftar);
        Btnkembali = findViewById(R.id.Btnkembali);

        Bc = BuildConfig.FLAVOR;

        Btndaftar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isValidEditText();
                isValidPassword();
            }
        });

        Btnkembali.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showDialogOnEditText();

        ETemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isValidEmail(s);
            }
        });
    }

    //----------------------------------------------Validasi--------------------------------------------
    public void isValidEditText() {
        namaDpn = ETnamaDpn.getText().toString();
        namaBlkng = ETnamaBlkng.getText().toString();
        tempat = ETtmptL.getText().toString();
        tanggal = ETdate.getText().toString();
        alamat = ETalmt.getText().toString();
        telpon = ETtlp.getText().toString();
        email = ETemail.getText().toString();
        password = ETpass.getText().toString();

        if (namaDpn.isEmpty() && namaBlkng.isEmpty() && tempat.isEmpty() && tanggal.isEmpty() && alamat.isEmpty() && telpon.isEmpty() && email.isEmpty() && password.isEmpty()) {
            ETnamaDpn.setError("Nama Depan Harus Diisi");
            ETnamaBlkng.setError("Nama Belakang Harus Diisi");
            ETtmptL.setError("Tempat Lahir Harus Diisi");
            ETdate.setError("Tanggal Lahir Harus Diisi");
            ETalmt.setError("Alamat Harus Diisi");
            ETtlp.setError("Nomor Telepon Harus Diisi");
            ETemail.setError("Email Harus Diisi");
            ETpass.setError("Password Harus Diisi");
        } else {
            konfirmasi();
        }
    }

    public void isValidEmail(Editable s) {
        email = ETemail.getText().toString().trim();
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            ETemail.setError("Format Email Salah");
        }
    }

    public boolean pattern(String pass2) {
        Bc = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{4,}" +
                "$";
        return Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$).{4,}" +
                "$").matcher(pass2).matches();
    }

    public void isValidPassword() {
        password = ETpass.getText().toString().trim();
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            ETpass.setError("Panjang password minimal adalah 8 karakter dengan angka, huruf besar, huruf kecil, dan spesial karakter (seperti: @, &amp;, #, ?)");
        } else if (!pattern(password)) {
            ETpass.setError("Panjang password minimal adalah 8 karakter dengan angka, huruf besar, huruf kecil, dan spesial karakter (seperti: @, &amp;, #, ?)");
        } else {
            if (ETpass.getText().toString().isEmpty()) {
                ETpass.setError("Password harus sama");
            } else if (!password.equals(ETpass.getText().toString().trim())) {
                ETpass.setError("Password harus sama");
            }
        }
    }

    public void HasilPendaftaran() {
        final AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
        View dialogView = getLayoutInflater().inflate(R.layout.hasil_pendaftaran, null);
        alertDialogBuilder.setTitle("Detail Pendaftaran");
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);

        TvnamaDpn = dialogView.findViewById(R.id.TvnamaDpn);
        TvnamaBlkng = dialogView.findViewById(R.id.TvnamaBlkng);
        TvTTL = dialogView.findViewById(R.id.TvTTL);
        Tvalamat = dialogView.findViewById(R.id.Tvalmt);
        Tvemail = dialogView.findViewById(R.id.Tvemail);
        Tvtlp = dialogView.findViewById(R.id.TvTlp);
        Tvjk = dialogView.findViewById(R.id.Tvjk);
        Tvagama = dialogView.findViewById(R.id.Tvagama);

        BtnOke = dialogView.findViewById(R.id.BtnOke);
        BtnKeluar = dialogView.findViewById(R.id.BtnKeluar);

        TvnamaDpn.setText(namaDpn);
        TvnamaBlkng.setText(namaBlkng);
        Tvalamat.setText(alamat);
        Tvagama.setText(agama);
        Tvemail.setText(email);
        Tvtlp.setText(telpon);
        Tvjk.setText(jk);

        TextView tv = TvTTL;
        StringBuilder sb = new StringBuilder();
        sb.append(tempat);
        sb.append(", ");
        sb.append(tanggal);
        tv.setText(sb.toString());


        BtnOke.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pendaftaran Selesai", 1).show();
                alertDialogBuilder.dismiss();
            }
        });
        BtnKeluar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        alertDialogBuilder.show();
    }

    //    ------------------------------------konfirmasi--------------------------------------------
    private void konfirmasi() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah data yang anda masukkan sudah benar?");
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HasilPendaftaran();
            }
        });
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //    ------------------------------------Pilih tanggal-----------------------------------------
    public void showDialogOnEditText() {

        ETdate.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, Listner, tahun, bulan, hari);
        return null;
    }

    private final DatePickerDialog.OnDateSetListener Listner =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int thn, int bln, int hr) {
                    tahun = thn;
                    bulan = bln;
                    hari = hr;
                    ETdate.setText(hari + "-" + bulan + "-" + tahun);
                }
            };

    //------------------------------------------Radio Button--------------------------------------------
    public void OnClickRB(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.buddha:
                if (checked) {
                    agama = "Buddha";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.hindu:
                if (checked) {
                    agama = "Hindu";
                    RG1.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.islam:
                if (checked) {
                    agama = "Islam";
                    RG2.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.katolik:
                if (checked) {
                    agama = "Katolik";
                    RG1.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.konghucu:
                if (checked) {
                    agama = "Konghucu";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.kristen:
                if (checked) {
                    agama = "Kristen";
                    RG2.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.aliran:
                if (checked) {
                    agama = "Aliran Kepercayaan";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG3.clearCheck();
                }
                break;
            case R.id.lk:
                if (checked) {
                    jk = "Laki-Laki";
                }
                break;
            case R.id.pr:
                if (checked) {
                    jk = "Perempuan";
                }
                break;
            default:
                break;
        }
    }

}

