package com.example.study_flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class FlowLayout: ViewGroup {
    private var start = 0 to 0
    private var wh = 0 to 0
    //设置过的view不再设置，加快布局计算，FlowLayout大小和位置并不会变化
    private val layoutSet: HashSet<Int> = hashSetOf()
    private val measureSet: HashSet<Int> = hashSetOf()
    private var left = 0
    private var height = 0

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * 给了ViewGroup测量的坐标
     * 需要规划、设置子View的位置
     * - 就根据从上至下，从左到右，不在排版策略上过多研究（也可以实现那一行有空往哪里差）
     * - view高度默认一致，不然得加上一行的高度最大值
     * - view有margin的在这里增加view测量的宽高MarginLayoutParams
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        start = l to t
        wh = r - l to b - t
        println("$start, $wh")
        for (i in 0 until childCount){
            val child = getChildAt(i)
            if (!layoutSet.contains(child.id)){
                val childW = child.measuredWidth
                val childH = child.measuredHeight
                if (childH + childW > 0) {
                    if (childW < wh.first - left || childW > wh.first){
                        //有剩余空间,或者太长超出屏幕，单独一行也没有意义
                    }else{
                        //无剩余空间，下一行
                        left = 0
                        height += childH
                    }
                    child.layout(left, height, left + childW, height + childH)
                    left += childW
                    println("$childW, $childH, ${child.id}")
                    layoutSet.add(child.id)
                }
            }
        }
    }

    /**
     * 先测量
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (!measureSet.contains(child.id)) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                measureSet.add(child.id)
            }
        }
    }

}