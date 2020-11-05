package br.com.arquerosdev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelEstudo
import br.com.arquerosdev.model.ModelPasta

class EstudoAdapter(
    val listasEstudo: List<ModelEstudo>,
    val onClick: (ModelEstudo) -> Unit
): RecyclerView.Adapter<EstudoAdapter.EstudoViewHolder>()    {
    class EstudoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textDiscusao: TextView

        init {
            textDiscusao = view.findViewById(R.id.textDiscusao)
        }
    }

    override fun getItemCount() = this.listasEstudo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_estudo, parent, false)

        val holder = EstudoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: EstudoViewHolder, position: Int) {
        val estudoModel = this.listasEstudo[position]

        holder.textDiscusao.text = estudoModel.mensagem

        //holder.itemView.setOnClickListener{onClick(pasta)}

    }
}