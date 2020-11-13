package br.com.arquerosdev.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelEstudo

class EstudoAdapter(
    val id_usuario: Int,
    val listasEstudo: List<ModelEstudo>,
    val onClick: (ModelEstudo) -> Unit
): RecyclerView.Adapter<EstudoAdapter.EstudoViewHolder>()    {
    class EstudoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textDiscusao: TextView
        val textNomeUsuario: TextView
        val ll_pai: LinearLayout

        init {
            textDiscusao = view.findViewById(R.id.textDiscusao)
            textNomeUsuario = view.findViewById(R.id.textNomeUsuario)
            ll_pai = view.findViewById(R.id.ll_pai)
        }
    }

    override fun getItemCount() = this.listasEstudo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_estudo, parent, false)
        return EstudoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstudoViewHolder, position: Int) {
        val estudoModel = this.listasEstudo[position]

        if(estudoModel.id_usuario == id_usuario){
            holder.ll_pai.gravity = Gravity.END
        }else{
            holder.ll_pai.gravity = Gravity.START
        }

        holder.textNomeUsuario.text = estudoModel.usuario
        holder.textDiscusao.text = estudoModel.mensagem

        //holder.itemView.setOnClickListener{onClick(pasta)}

    }
}