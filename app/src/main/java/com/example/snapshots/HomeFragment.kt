package com.example.snapshots


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.snapshots.databinding.FragmentHomeBinding
import com.example.snapshots.databinding.ItemSnapshotBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : Fragment() {

    private lateinit var mbinding         : FragmentHomeBinding
    private lateinit var mFirebaseAdapter : FirebaseRecyclerAdapter<Snapshot, SnapshotHolder> //TODO 2 adapter
    private lateinit var mLayoutManager   : RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mbinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query   = FirebaseDatabase.getInstance().reference.child("snapshots") //TODO 3 adapter
        val options = FirebaseRecyclerOptions.Builder<Snapshot>().setQuery(query, Snapshot::class.java).build() //TODO 4 adapter

        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Snapshot, SnapshotHolder>(options){ //TODO 5 adapter

            private lateinit var mContext : Context

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapshotHolder { //TODO 6 adapter
                mContext = parent.context
                val view = LayoutInflater.from(mContext).inflate(R.layout.item_snapshot, parent, false)
                return SnapshotHolder(view)
            }

            override fun onBindViewHolder(holder: SnapshotHolder, position: Int, model: Snapshot) { //TODO 7 adapter
                val snapshot = getItem(position)

                with(holder){
                    listener(snapshot)
                    binding.tvTitle.text = snapshot.title
                    Glide.with(mContext)
                        .load(snapshot.photoUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(binding.imgPhoto)
                }
            }

            override fun onDataChanged() { //TODO 8 adapter
                super.onDataChanged()
                mbinding.pbCarga.visibility = View.GONE
            }

            override fun onError(error: DatabaseError) { //TODO 9 adapter
                super.onError(error)
                Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
            }
        }

        mLayoutManager = LinearLayoutManager(context) //TODO 10 adapter
        mbinding.rvHome.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter       = mFirebaseAdapter
        }
    }

    override fun onStart() { //TODO 11 adapter
        super.onStart()
        mFirebaseAdapter.startListening()
    }

    override fun onStop() { //TODO 12 adapter
        super.onStop()
        mFirebaseAdapter.stopListening()
    }

    inner class SnapshotHolder(view: View) : RecyclerView.ViewHolder(view){  //TODO 1 adapter
        val binding = ItemSnapshotBinding.bind(view)

        fun listener(snapshot: Snapshot){

        }
    }




}