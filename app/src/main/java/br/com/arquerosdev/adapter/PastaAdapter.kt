package br.com.arquerosdev.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelPasta

class PastaAdapter(
    val listasPastas: List<ModelPasta>,
    val onClick: (ModelPasta) -> Unit
): RecyclerView.Adapter<PastaAdapter.PastaViewHolder>()    {
    class PastaViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardDiscusao: TextView
        val cardDescricao: TextView
        val cardLocalizacao: TextView
        val cardProgress: ProgressBar

        init {
            cardNome = view.findViewById(R.id.cardNome)
            cardDiscusao = view.findViewById(R.id.cardDiscusao)
            cardDescricao = view.findViewById(R.id.cardDescricao)
            cardLocalizacao = view.findViewById(R.id.cardLocalizacao)
            cardProgress = view.findViewById(R.id.cardProgress)
        }
    }

    override fun getItemCount() = this.listasPastas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_pastas, parent, false)

        val holder = PastaViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PastaViewHolder, position: Int) {
        val pasta = this.listasPastas[position]

        holder.cardNome.text = pasta.nome
        holder.cardDiscusao.text = pasta.discussao
        holder.cardDescricao.text = pasta.descricao
        holder.cardLocalizacao.text = pasta.localizacao
        holder.cardProgress.visibility

        holder.itemView.setOnClickListener{onClick(pasta)}

    }
}