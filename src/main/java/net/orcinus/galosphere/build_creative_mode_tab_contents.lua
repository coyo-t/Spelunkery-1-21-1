local LOCAL_NAMESPACE <const> = 'galosphere'
local function handleResourceLocation (r)
	if type(r) == 'table' then
		for i = 1, #r do
			r[i] = handleResourceLocation(r[i])
		end
		return r
	end
	if type(r) ~= 'string' then
		r = tostring(r)
	end

	if r:sub(1, 1) == ':' then
		return LOCAL_NAMESPACE..r
	end
	return r
end
local function handleArgzorz (r)
	if type(r) == 'string' then
		r = { r }
	end
	return handleResourceLocation(r)
end

local function __MK_MAJIGGER (typemode, onestep)
	return function (arg1)
		local outs = {
			type = typemode,
		}
		if onestep then
			outs.things = handleArgzorz(arg1)
			return outs
		else
			outs.target = arg1
			return function (thingz)
				outs.things = handleArgzorz(thingz)
				return outs
			end
		end
	end
end

local before <const> = __MK_MAJIGGER('insert before', false)
local after <const> = __MK_MAJIGGER('insert after', false)
local accept <const> = __MK_MAJIGGER('accept', true)


return {
	['minecraft:building_blocks'] = {
		after 'minecraft:amethyst_block' {
			':amethyst_stairs',
			':amethyst_slab',
			':chiseled_amethyst',
			':amethyst_lamp',
			':smooth_amethyst',
			':smooth_amethyst_stairs',
			':smooth_amethyst_slab',
			':amethyst_bricks',
			':amethyst_brick_stairs',
			':amethyst_brick_slab',
			':allurite_block',
			':allurite_stairs',
			':allurite_slab',
			':chiseled_allurite',
			':allurite_lamp',
			':smooth_allurite',
			':smooth_allurite_stairs',
			':smooth_allurite_slab',
			':allurite_bricks',
			':allurite_brick_stairs',
			':allurite_brick_slab',
			':lumiere_block',
			':lumiere_stairs',
			':lumiere_slab',
			':chiseled_lumiere',
			':lumiere_lamp',
			':smooth_lumiere',
			':smooth_lumiere_stairs',
			':smooth_lumiere_slab',
			':lumiere_bricks',
			':lumiere_brick_stairs',
			':lumiere_brick_slab',
		},
		accept {
			':silver_block',
			':silver_panel',
			':silver_panel_stairs',
			':silver_panel_slab',
			':silver_tiles',
			':silver_tiles_stairs',
			':silver_tiles_slab',
			':silver_lattice',
		},
	},
	['minecraft:natural_blocks'] = {
		after 'minecraft:deepslate_iron_ore' {
			':silver_ore',
			':deepslate_silver_ore',
		},
		after 'minecraft:raw_gold_block' ':raw_silver_block',
		after 'minecraft:amethyst_cluster' {
			':glinted_amethyst_cluster',
			':allurite_block',
			':allurite_cluster',
			':glinted_allurite_cluster',
			':lumiere_block',
			':lumiere_cluster',
			':glinted_lumiere_cluster',
		},
		after 'minecraft:glow_lichen' ':glow_ink_clumps',
		after 'minecraft:pearlescent_froglight' {
			':amethyst_lamp',
			':allurite_lamp',
			':lumiere_lamp',
		},
		-- this was split into two separate if blocks. why??
		after 'minecraft:snow' ':lichen_moss',
		after 'minecraft:red_mushroom' ':bowl_lichen',
		after 'minecraft:cactus' {
			':lichen_shelf',
			':lichen_roots',
		},
	},
	['minecraft:ingredients'] = {
		after 'minecraft:raw_gold' ':raw_silver',
		after 'minecraft:gold_ingot' ':silver_ingot',
		after 'minecraft:gold_nugget' ':silver_nugget',
		after 'minecraft:amethyst_shard' {
			':allurite_shard',
			':lumiere_shard',
		},
	},
	['minecraft:functional_blocks'] = {
		after 'minecraft:end_rod' ':chandelier',
		after 'minecraft:smithing_table' ':combustion_table',
		after 'minecraft:respawn_anchor' {
			':monstrometer',
			':warped_anchor',
		},
	},
	['minecraft:food_and_drinks'] = {
		after 'minecraft:glow_berries' {
			':lichen_cordyceps',
			':golden_lichen_cordyceps',
		},
	},
	['minecraft:spawn_eggs'] = {
		after 'minecraft:sheep_spawn_egg' {
			':sparkle_spawn_egg',
			':spectre_spawn_egg',
			':specterpillar_spawn_egg',
		},
	},
	['minecraft:tools_and_utilities'] = {
		after 'minecrafT:clock'  ':barometer',
		before 'minecraft:saddle' {
			':glow_flare',
			':spectre_flare',
		},
	},
	['minecraft:combat'] = {
		after 'minecraft:chainmail_boots' {
			':sterling_helmet',
			':sterling_chestplate',
			':sterling_leggings',
			':sterling_boots',
		},
		after 'minecraft:leather_horse_armor' ':sterling_horse_armor',
		after 'minecraft:tnt' ':silver_bomb',
	},
}
