import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oscar.a281_proy.ImageItem
import com.oscar.a281_proy.ProductItem
import com.oscar.a281_proy.R


class ProductAdapter(private val products: List<ProductItem>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.productImage)
        val titleView: TextView = itemView.findViewById(R.id.productTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.imageView.setImageResource(product.imageResId)
        holder.titleView.text = product.title
    }

    override fun getItemCount(): Int = products.size
}