package pt.isec.gps.grupo12;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import pt.isec.gps.grupo12.cliente.EnviarMensagem;
import pt.isec.gps.grupo12.cliente.RecebeMensagem;
import pt.isec.gps.grupo12.cliente.TrataMensagens;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActividadeLogin extends Activity implements RecebeMensagem{
	
	private EnviarMensagem enviarM;
	
	

	public ActividadeLogin() {
		super();
		try {
			enviarM = new TrataMensagens(this);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onUsername(View v)
	{
		EditText user = (EditText) findViewById(R.id.editUsername);
		user.setText("");
	}
	
	public void onPassword(View v)
	{
		EditText pass = (EditText) findViewById(R.id.editPassword);
		pass.setText("");
	}
	
	public void onLogin(View v)
	{
		EditText user = (EditText) findViewById(R.id.editUsername);
		EditText pass = (EditText) findViewById(R.id.editPassword);
		String username = user.getText().toString();
		String password = pass.getText().toString();
		
		if((username.equals("") || username.equals("Username")) && (password.equals("") || (password.equals("Password"))))
		{
			Toast.makeText(ActividadeLogin.this, "Insira Username e Password", Toast.LENGTH_SHORT).show();
			return;
		}
		if((username.equals("") || username.equals("Username")))
		{
			Toast.makeText(ActividadeLogin.this, "Insira Username", Toast.LENGTH_SHORT).show();
			return;
		}
		if((password.equals("") || (password.equals("Password"))))
		{
			Toast.makeText(ActividadeLogin.this, "Insira Password", Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			enviarM.efectuarLogin(user.getText().toString(), pass.getText().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void relatorioRecebido(List<String> receberam,
			List<String> naoReceberam, List<String> offline,
			List<String> ignoraram) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void corRecebida(String remetente, String rgb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respostaDeLoginChegou() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respostaDeLogoutChegou() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void servidorNecessitaDeEncerrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void servidorEstaADemorarMuitoTempoAResponder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void erroComunicacao() {
		// TODO Auto-generated method stub
		
	}
}
