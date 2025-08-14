local fwide, ftall = 13, 2
local pwide, ptall = 13, 514
local direction_x, direction_y = 0, 1

local fc = 27
local points = {}
local x, y = 0, 0
for _ = 1, fc do
	table.insert(points, { x, y, fwide, ftall })
	x = x + (fwide * direction_x)
	y = y + (ftall * direction_y)
end

return {
	at = { 2, 13 },
	source = {
		path = 'spelunkery:slot/bar/durability',
		full = -1,
		points = points,
	},
	applies_to = 'minecraft:golden_shovel',
	value = {
		--type = 'component',
		source = 'minecraft:damage',
		max    = 'minecraft:max_damage',
	},
}