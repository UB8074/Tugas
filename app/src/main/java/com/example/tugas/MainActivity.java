package com.example.tugas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    RadioGroup RG1, RG2, RG3, RG4, RGjk;
    RadioButton lk, pr, islam, kristen, katolik, hindu, buddha, konghucu, aliran;
    EditText ETnamaDpn, ETnamaBlkng, ETtmptL, ETdate, ETalmt, ETtlp, ETemail, ETpass, ETpassUlang;
    Button Btndaftar, Btnkembali, BtnOke, BtnKeluar;

    String Bc, jk, agama, namaDpn, namaBlkng,
            tempat, tanggal, alamat, telpon, email, password, passUlang;
    TextView TvnamaDpn, TvnamaBlkng, TvTTL, Tvalamat, Tvjk, Tvagama, Tvtlp, Tvemail;

    int tahun, bulan, hari;

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
        ETpassUlang = findViewById(R.id.ETpassUlang);

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
        ETemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!ETemail.getText().toString().trim()
                        .matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    ETemail.setError("Format Email Salah");
                }
            }
        });

        ETdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePick();
            }
        });
        Btndaftar.setOnClickListener(new View.OnClickListener() {
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
    }

    //----------------------------------------------Validasi----------------------------------------
    public void isValidEditText() {
        namaDpn = ETnamaDpn.getText().toString();
        namaBlkng = ETnamaBlkng.getText().toString();
        tempat = ETtmptL.getText().toString();
        tanggal = ETdate.getText().toString();
        alamat = ETalmt.getText().toString();
        telpon = ETtlp.getText().toString();
        email = ETemail.getText().toString();
        password = ETpass.getText().toString();
        passUlang = ETpass.getText().toString();

        if (namaDpn.isEmpty() && namaBlkng.isEmpty() && tempat.isEmpty() && tanggal.isEmpty() &&
                alamat.isEmpty() && telpon.isEmpty() && email.isEmpty() && password.isEmpty() &&
                passUlang.isEmpty()) {
            ETnamaDpn.setError("Nama Depan Harus Diisi");
            ETnamaBlkng.setError("Nama Belakang Harus Diisi");
            ETtmptL.setError("Tempat Lahir Harus Diisi");
            ETdate.setError("Tanggal Lahir Harus Diisi");
            ETalmt.setError("Alamat Harus Diisi");
            ETtlp.setError("Nomor Telepon Harus Diisi");
            ETemail.setError("Email Harus Diisi");
            ETpass.setError("Password Harus Diisi");
            ETpassUlang.setError("Password Harus Diisi");
        } else {
            konfirmasi();
        }
    }

    public boolean pattern(String pass) {
        String patt = "^(?=.*[0-9])" +
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
                "$").matcher(pass).matches();
    }

    public void isValidPassword() {
        String err = "Panjang password minimal adalah 8 karakter dengan angka, huruf " +
                "besar, huruf kecil, dan spesial karakter (seperti: @,&,#,?)";
        if (TextUtils.isEmpty(ETpass.getText().toString().trim()) ||
                ETpass.getText().toString().trim().length() < 8) {
            ETpass.setError(err);
        } else if (!pattern(ETpass.getText().toString().trim())) {
            ETpass.setError(err);
        } else {
            if (!ETpass.getText().toString().trim().equals(
                    ETpassUlang.getText().toString().trim())) {
                ETpassUlang.setError("Password harus sama");
            }
        }
    }

    //-----------------------------------------View Hasil-------------------------------------------
    public void HasilPendaftaran() {
        final AlertDialog aDB = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.hasil_pendaftaran, null);
        aDB.setTitle("Detail Pendaftaran");
        aDB.setView(dialogView);
        aDB.setCancelable(true);
        aDB.setIcon(R.mipmap.ic_launcher);

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

        StringBuilder sB = new StringBuilder();
        sB.append(tempat);
        sB.append(", ");
        sB.append(tanggal);
        TvTTL.setText(sB.toString());

        BtnOke.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pendaftaran Selesai",
                        Toast.LENGTH_LONG).show();
                aDB.dismiss();
            }
        });

        BtnKeluar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        aDB.show();
    }

    //    ------------------------------------konfirmasi--------------------------------------------
    private void konfirmasi() {
        final AlertDialog.Builder aDB = new AlertDialog.Builder(this);
        aDB.setTitle("Detail Pendaftaran");
        aDB.setCancelable(true);
        aDB.setIcon(R.mipmap.ic_launcher);
        aDB.setMessage("Apakah data yang anda masukkan sudah benar?");

        aDB.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HasilPendaftaran();
            }
        });

        aDB.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog aD = aDB.create();

        aD.show();
    }

    //----------------------------------------Pilih tanggal-----------------------------------------
    public void datePick() {
        Calendar c = Calendar.getInstance();
        tahun = c.get(Calendar.YEAR);
        bulan = c.get(Calendar.MONTH);
        hari = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dPD = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int _tahun, int _bulan, int _hari) {
                        ETdate.setText(_hari + "-" + (_bulan + 1) + "-" + _tahun);
                    }
                }, tahun, bulan, hari
        );

        dPD.show();
    }

    //----------------------------------Choose Radio Button-----------------------------------------
    public void ChooseRB(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
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
            case R.id.islam:
                if (checked) {
                    agama = "Islam";
                    RG2.clearCheck();
                    RG3.clearCheck();
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
            case R.id.hindu:
                if (checked) {
                    agama = "Hindu";
                    RG1.clearCheck();
                    RG3.clearCheck();
                    RG4.clearCheck();
                }
                break;
            case R.id.buddha:
                if (checked) {
                    agama = "Buddha";
                    RG1.clearCheck();
                    RG2.clearCheck();
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
            case R.id.aliran:
                if (checked) {
                    agama = "Aliran Kepercayaan";
                    RG1.clearCheck();
                    RG2.clearCheck();
                    RG3.clearCheck();
                }
                break;
            default:
                break;
        }
    }

}

