package dev.hmr.kanban.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titleList: MutableList<Int> = ArrayList()

    fun getTitle(position: Int): Int {
        return this.titleList[position]
    }

    fun addFragment(fragment: Fragment, title: Int) {
        this.fragmentList.add(fragment)
        this.titleList.add(title)
    }

    override fun createFragment(position: Int): Fragment {
        return this.fragmentList[position]
    }

    override fun getItemCount(): Int {
        return this.fragmentList.size
    }
}