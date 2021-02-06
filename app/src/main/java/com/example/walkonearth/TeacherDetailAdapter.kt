package com.example.walkonearth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walkonearth.databinding.*
import com.example.walkonearth.model.*
import com.google.android.exoplayer2.Player
import java.util.*


const val VIEW_TYPE_PROFILE = 0;
const val VIEW_TYPE_ACREDITATIONS = 1;
const val VIEW_TYPE_ACREDITATIONS_TITLE = 2;
const val VIEW_TYPE_CLASSES_TITLE = 3;
const val VIEW_TYPE_TEACHER_BIO = 4;
const val VIEW_TYPE_INTRO = 5;
const val VIEW_TYPE_SESSION = 6;


class TeacherDetailAdapter(private val teacherDetailItems: List<Any>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_PROFILE -> {
                return TeacherProfileViewHolder(
                    TeacherProfileRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_ACREDITATIONS -> {
                return TeacherAcreditationsViewHolder(
                    AcreditationsRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_ACREDITATIONS_TITLE -> {
                return TeacherAcreditationsTitleViewHolder(
                    AcreditationsRowTitleLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_CLASSES_TITLE -> {
                return TeacherSessionTitleViewHolder(
                    ClassesRowTitleLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_TEACHER_BIO -> {
                return TeacherBioViewHolder(
                    TeacherBioRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_INTRO -> {
                return TeacherIntroViewHolder(
                    TeacherIntroRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_SESSION -> {
                return TeacherClassViewHolder(
                    TeacherSessionsRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            else -> {
                return TeacherClassViewHolder(
                    TeacherSessionsRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return teacherDetailItems?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        when (teacherDetailItems?.get(position)) {
            is TeacherDetails -> return VIEW_TYPE_PROFILE
            is TeacherBio -> return VIEW_TYPE_TEACHER_BIO
            is IntroVideo -> return VIEW_TYPE_INTRO
            is ClassesItem -> return VIEW_TYPE_SESSION
            is SessionsTitle -> return VIEW_TYPE_CLASSES_TITLE
            is TitleAccreditations -> return VIEW_TYPE_ACREDITATIONS_TITLE
            is Accreditation -> return VIEW_TYPE_ACREDITATIONS
            else -> return VIEW_TYPE_ACREDITATIONS_TITLE
        }
    }


    class TeacherProfileViewHolder(val binding: TeacherProfileRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TeacherDetails?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherBioViewHolder(val binding: TeacherBioRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: com.example.walkonearth.model.TeacherBio?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherAcreditationsViewHolder(val binding: AcreditationsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Accreditation?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherAcreditationsTitleViewHolder(val binding: AcreditationsRowTitleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TitleAccreditations?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherSessionTitleViewHolder(val binding: ClassesRowTitleLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SessionsTitle?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherClassViewHolder(val binding: TeacherSessionsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ClassesItem?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class TeacherIntroViewHolder(val binding: TeacherIntroRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root),PlayerStateCallback {
        fun bind(model: IntroVideo?) {
            binding?.model = model
            binding?.callback = this@TeacherIntroViewHolder
            binding?.executePendingBindings()
        }

        override fun onVideoDurationRetrieved(duration: Long, player: Player) {
            //TODO("Not yet implemented")
        }

        override fun onVideoBuffering(player: Player) {
            //TODO("Not yet implemented")
        }

        override fun onStartedPlaying(player: Player) {
            //TODO("Not yet implemented")
        }

        override fun onFinishedPlaying(player: Player) {
           // TODO("Not yet implemented")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_PROFILE -> (holder as? TeacherProfileViewHolder)?.bind(
                teacherDetailItems?.get(
                    position
                ) as? TeacherDetails
            )
            VIEW_TYPE_ACREDITATIONS -> (holder as? TeacherAcreditationsViewHolder)?.bind(
                teacherDetailItems?.get(position) as? Accreditation
            )
            VIEW_TYPE_ACREDITATIONS_TITLE -> (holder as? TeacherAcreditationsTitleViewHolder)?.bind(
                teacherDetailItems?.get(position) as? TitleAccreditations
            )
            VIEW_TYPE_CLASSES_TITLE -> (holder as? TeacherSessionTitleViewHolder)?.bind(
                teacherDetailItems?.get(position) as? SessionsTitle
            )
            VIEW_TYPE_TEACHER_BIO -> (holder as? TeacherBioViewHolder)?.bind(
                teacherDetailItems?.get(
                    position
                ) as? TeacherBio
            )
            VIEW_TYPE_INTRO -> (holder as? TeacherIntroViewHolder)?.bind(
                teacherDetailItems?.get(
                    position
                ) as? IntroVideo
            )
            VIEW_TYPE_SESSION -> (holder as? TeacherClassViewHolder)?.bind(
                teacherDetailItems?.get(
                    position
                ) as? ClassesItem
            )

        }
    }




}