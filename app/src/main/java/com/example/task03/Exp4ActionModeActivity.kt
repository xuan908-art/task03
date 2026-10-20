package com.example.task03

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Exp4ActionModeActivity : AppCompatActivity() {

    private lateinit var dataList: MutableList<HashMap<String, Any>>
    private lateinit var adapter: SimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exp4)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listView: ListView = findViewById(R.id.listViewActionMode)

        val names = arrayOf("One", "Two", "Three", "Four", "Five")
        dataList = names.map { name ->
            hashMapOf<String, Any>(
                "icon" to R.mipmap.ic_launcher,
                "name" to name
            )
        }.toMutableList()

        adapter = SimpleAdapter(
            this,
            dataList,
            R.layout.list_item_actionmode,
            arrayOf("icon", "name"),
            intArrayOf(R.id.iv_icon, R.id.tv_title)
        )
        listView.adapter = adapter

        // 多选模式 + ActionMode 上下文菜单
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        listView.setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.menuInflater.inflate(R.menu.menu_exp4_context, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

            override fun onItemCheckedStateChanged(
                mode: ActionMode, position: Int, id: Long, checked: Boolean
            ) {
                val count = listView.checkedItemCount
                mode.title = "$count selected"
            }

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                return when (item.itemId) {
                    R.id.menu_delete -> {
                        // 删除所有勾选项
                        val checked = listView.checkedItemPositions
                        val toRemove = mutableListOf<HashMap<String, Any>>()
                        for (i in 0 until checked.size()) {
                            if (checked.valueAt(i)) {
                                toRemove.add(dataList[checked.keyAt(i)])
                            }
                        }
                        dataList.removeAll(toRemove)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(
                            this@Exp4ActionModeActivity,
                            "已删除 ${toRemove.size} 项",
                            Toast.LENGTH_SHORT
                        ).show()
                        mode.finish()
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode) {}
        })
    }
}
