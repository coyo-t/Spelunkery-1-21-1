local fc = 27
local order = {}
for i = 1, fc do
	table.insert(order, i - 1)
end

return {
	source = 'spelunkery:slot/bar/remaining_durability',
	empty = 0,
	full = fc - 1,
	order = order,
	value = {
		--type = 'component',
		source = 'minecraft:damage',
		max    = 'minecraft:max_damage',
	},
}