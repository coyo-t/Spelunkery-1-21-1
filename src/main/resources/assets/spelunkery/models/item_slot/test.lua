local frame_name = 'spelunkery:textures/gui/slot/bar/durability/retro%s'
local frame_count = 257

local points = {}
for i = 1, frame_count do
	local n = frame_name:format(i)
	print(n)
	table.insert(points, n)
end

return {
	at = { 2, 13 },
	base_size = { 13, 2 },
	sub_images = points,
	applies_to   = {
		'minecraft:golden_shovel',
	},
	value_source = 'minecraft:damage',
	value_max    = 'minecraft:max_damage',
}