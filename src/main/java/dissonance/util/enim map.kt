package dissonance.util

import java.util.EnumMap

inline fun <reified K: Enum<K>, V> enumMapOf ()
	= EnumMap<K, V>(K::class.java)

inline fun <reified K: Enum<K>, V> enumMapOf (vararg items:Pair<K, V>)
	= enumMapOf<K, V>().apply {
		items.forEach { (k, v) -> put(k, v) }
	}
