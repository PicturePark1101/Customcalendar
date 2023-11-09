package ddwucom.moblie.customcalendar.presentation.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ddwucom.moblie.customcalendar.databinding.FragmentCalendarTotalBinding
import ddwucom.moblie.customcalendar.presentation.adapter.CalendarMonthAdapter
import ddwucom.moblie.customcalendar.presentation.adapter.CalendarMonthAdapter.Companion.START_POSITION

class CalendarTotalFragment : Fragment() {
    private val TAG = "CalendarTotalFragment"
    lateinit var calendarTotalBinding: FragmentCalendarTotalBinding
    lateinit var calendarMonthAdapter: CalendarMonthAdapter
    private var currentPosition = 0
    private var month = 0
    private var year = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        calendarTotalBinding = FragmentCalendarTotalBinding.inflate(inflater)
        return calendarTotalBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calendarMonthAdapter = CalendarMonthAdapter(requireActivity())
        calendarTotalBinding.vp2CalendarTotal.adapter = calendarMonthAdapter
        calendarTotalBinding.vp2CalendarTotal.setCurrentItem(CalendarMonthAdapter.START_POSITION, false)
        // 연과 월을 띄움
        setDateInfo()

        calendarTotalBinding.vp2CalendarTotal.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d(TAG, "ViewPager2에서 변경감지")
                setDateInfo()
            }
        })
        calendarTotalBinding.btnCalendarTotalBack.setOnClickListener {
            moveDate(-1, true)
        }
        calendarTotalBinding.btnCalendarTotalNext.setOnClickListener {
            moveDate(1, true)
        }
    }

    private fun setDateInfo() {
        currentPosition = calendarTotalBinding.vp2CalendarTotal.currentItem
        val itemId = calendarMonthAdapter.getItemId(currentPosition).toInt()
        Log.d(TAG, "START_POSITION = ${CalendarMonthAdapter.START_POSITION}, currentPosition = $currentPosition")
        month = itemId % 100
        year = itemId / 100
        calendarTotalBinding.tvCalendarTotalMonth.text = month.toString() + "월"
        calendarTotalBinding.tvCalendarTotalYear.text = year.toString() + "년"
    }

    private fun moveDate(move: Int, smoothScroll: Boolean) {
        currentPosition = calendarTotalBinding.vp2CalendarTotal.currentItem
        calendarTotalBinding.vp2CalendarTotal.setCurrentItem(currentPosition + move, smoothScroll)
    }
}
