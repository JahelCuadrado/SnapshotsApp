package com.example.snapshots

import com.google.firebase.database.IgnoreExtraProperties


//TODO #2 Firebase

/*
Objeto con el que va a trabajar firebase, el IgnoreExtraProperties es una propiedad
que la documentacion de firebase recomienda poner
*/

@IgnoreExtraProperties
data class Snapshot(
    var id       : String               = "",
    var title    : String,
    var photoUrl : String,
    var likeList : Map<String, Boolean> = mutableMapOf()
)
