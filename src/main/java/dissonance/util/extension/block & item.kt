package dissonance.util.extension

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.IntegerProperty
import java.util.function.Supplier


infix fun <T: Block> BlockState.isa (b: T) = `is`(b)
infix fun <T: Block> BlockState.isa (b: Supplier<T>) = isa(b.get())

infix fun <T:Item> ItemStack.isa (i:T) = `is`(i)
infix fun <T:Item> ItemStack.isa (i: Supplier<T>) = isa(i.get())

operator fun ItemStack.contains (component: DataComponentType<*>)
	= has(component)

operator fun ItemStack.contains (supplier: Supplier<DataComponentType<*>>)
	= has(supplier)

operator fun ItemStack.contains (supplier: Holder<DataComponentType<*>>)
	= has(supplier.value())

fun BlockState.decremented (what: IntegerProperty)
	= setValue(what, getValue(what) - 1)

operator fun BlockState.get (what: IntegerProperty)
	= getValue(what)

operator fun BlockState.set (what: IntegerProperty, value: Int)
	= setValue(what, value)
