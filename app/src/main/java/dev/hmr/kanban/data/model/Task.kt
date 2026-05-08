package dev.hmr.kanban.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable


@Parcelize
data class Task(
    var id: String = "",
    var description: String = "",
    var status: Status = Status.TODO
) : Parcelable