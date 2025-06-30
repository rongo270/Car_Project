    package com.example.car_project.ui.adapters

    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.example.car_project.R
    import com.example.car_project.logic.helpers.ScoreEntry

    class ScoreAdapter(private val scores: List<ScoreEntry>) :
        RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

        class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.score_name)
            val score: TextView = view.findViewById(R.id.score_value)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_score_row, parent, false)
            return ScoreViewHolder(view)
        }

        override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
            val entry = scores[position]
            holder.name.text = entry.name
            holder.score.text = entry.score.toString()
        }

        override fun getItemCount(): Int = scores.size
    }
