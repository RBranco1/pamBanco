package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtCPF, txtSenha, txtConfirmaSenha, txtNome, txtTelefone,txtRG,txtEmail;
    Button btnRegistar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtCPF = findViewById(R.id.idCPF);
        txtSenha = findViewById(R.id.idSenha);
        txtConfirmaSenha = findViewById(R.id.idConfirmaSenha);
        txtNome = findViewById(R.id.idNome);
        txtTelefone= findViewById(R.id.idTelefone);
        txtRG = findViewById(R.id.idRG);
        txtEmail = findViewById(R.id.idEmail);



        btnLogin = findViewById(R.id.idBtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf, senha, confirmaSenha, telefone,rg,email, nome;

                cpf = txtCPF.getText().toString();
                senha = txtSenha.getText().toString();
                rg = txtRG.getText().toString();
                telefone= txtTelefone.getText().toString();
                email = txtEmail.getText().toString();
                nome = txtNome.getText().toString();

                confirmaSenha = txtConfirmaSenha.getText().toString();

                if (cpf.equals("") || senha.equals("") || confirmaSenha.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean ChecarCPFeSenha = db.validarCPF(cpf);
                        if (ChecarCPFeSenha == true) {
                            Boolean inserir = db.insert(cpf, senha, nome, telefone, rg, email);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "CPF inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
