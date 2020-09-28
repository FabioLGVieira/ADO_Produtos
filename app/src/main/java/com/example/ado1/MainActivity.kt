package com.example.ado1

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val sh = getSharedPreferences("precoProdutos", Context.MODE_PRIVATE)


        btPop.setOnClickListener { v: View? ->

            var produto = sh.getString(txtNomeProduto.text.toString(),"")
            var prod = produto.split(";")
            val nome = prod[0]
            val custo = prod[1]
            val venda = prod[2]
            var saldo = venda.toFloat() - custo.toFloat()

            val builder = AlertDialog.Builder(this)
            builder.setTitle(nome)
            if(saldo > 0){
            builder.setMessage("Lucro de R$" + saldo)}
            else{
                builder.setMessage("Prejuízo de R$" + (saldo * -1))}
            builder.setPositiveButton(android.R.string.ok) { dialog, which ->

            }
            builder.show()
        }


        btLimpar.setOnClickListener { v: View? ->
            txtNomeProduto.text.clear()
            txtPrecoCusto.text.clear()
            txtPrecoVenda.text.clear()
        }


        btSalvar.setOnClickListener { v: View? ->
            if(txtNomeProduto.text.isNotEmpty() || txtPrecoCusto.text.isNotEmpty() || txtPrecoVenda.text.isNotEmpty()){

                val produto = txtNomeProduto.text.toString()  + ";"+ txtPrecoCusto.text.toString() +";"+ txtPrecoVenda.text.toString()

                sh.edit().putString(txtNomeProduto.text.toString(),produto).apply()
                Toast.makeText(this,"Produto Salvo", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Nome da produto inexistente",Toast.LENGTH_SHORT).show()
            }
        }

        btAbrir.setOnClickListener { v: View? ->
            if(txtNomeProduto.text.isNotEmpty()){

                var produto = sh.getString(txtNomeProduto.text.toString(),"")
                if(produto.isNullOrEmpty()){
                    Toast.makeText(this,"Produto Vazio ou Inexistente",Toast.LENGTH_SHORT).show()
                }
                else{
                    var prod = produto.split(";")
                    var nome = prod[0]
                    var custo = prod[1]
                    var venda = prod[2]
                    txtNomeProduto.setText(nome)
                    txtPrecoCusto.setText(custo)
                    txtPrecoVenda.setText(venda)
                    Toast.makeText(this,"Produto Carregado com Sucesso",Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this,"Nome da produto inexistente",Toast.LENGTH_SHORT).show()
            }

        }

    }
}
