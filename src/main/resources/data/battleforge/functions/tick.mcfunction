######## Setup ########

scoreboard objectives add particledelay dummy
scoreboard objectives add forgetime dummy
scoreboard objectives add iron_timer dummy
scoreboard objectives add gold_timer dummy
scoreboard objectives add netherite_timer dummy
scoreboard objectives add diamond_timer dummy

######## Item particle handler ########

execute as @a[nbt={Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s rotated as @s run scoreboard players add @s particledelay 1
execute as @a[scores={particledelay=40..},nbt={Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run particle minecraft:flame ~ ~0.1 ~ 2 0 2 0 50 force
execute as @a[scores={particledelay=40..},nbt={Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run scoreboard players set @s particledelay 0

execute as @a[scores={iron_timer=1..}] at @s run particle dust .9 .9 .9 1 ~0.4 ~1 ~0.4 0 0 0 0 1 force @a
execute as @a[scores={gold_timer=1..}] at @s run particle dust 1 1 0 1 ~0.4 ~1 ~-0.4 0 0 0 0 1 force @a
execute as @a[scores={netherite_timer=1..}] at @s run particle dust .2 0 .1 1 ~-0.4 ~1 ~0.4 0 0 0 0 1 force @a
execute as @a[scores={diamond_timer=1..}] at @s run particle dust 0.3 .6 1 1 ~-0.4 ~1 ~-0.4 0 0 0 0 1 force @a

######## Ingot Checker ########

execute as @a[nbt={SelectedItem:{id:"minecraft:iron_ingot"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:gold_ingot"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:diamond"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:netherite_ingot"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging

execute as @a[nbt={SelectedItem:{id:"minecraft:iron_block"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:gold_block"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:diamond_block"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging
execute as @a[nbt={SelectedItem:{id:"minecraft:netherite_block"},Inventory:[{Slot:-106b,id:"minecraft:carrot_on_a_stick",tag:{battleforge:1b}}]}] at @s run tag @s add forging

######## Forging Countdown ########

execute as @a[tag=forging] at @s run scoreboard players add @s forgetime 1
execute as @a[tag=forging] at @s run particle minecraft:flame ~ ~1 ~ 1 1 1 0 1 force
execute as @a[tag=forging] at @s run attribute @s generic.movement_speed modifier add 06eb4794-038d-4015-b38e-707ef176ba28 "forgeslow" -0.09 add
execute as @a[tag=forging] at @s run effect give @s jump_boost 1 250 true

execute as @a[tag=forging,scores={forgetime=1}] at @s run playsound block.anvil.use player @a ~ ~ ~ 1 0.95
execute as @a[tag=forging,scores={forgetime=1}] at @s run playsound block.beacon.power_select player @a ~ ~ ~ 1 0.95
execute as @a[tag=forging,scores={forgetime=1}] at @s run title @s actionbar ["",{"text":"[--------","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=16}] at @s run title @s actionbar ["",{"text":"[■-------","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=31}] at @s run title @s actionbar ["",{"text":"[■■------","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=31}] at @s run playsound block.anvil.use player @a ~ ~ ~ 1 1.03
execute as @a[tag=forging,scores={forgetime=46}] at @s run title @s actionbar ["",{"text":"[■■■-----","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=61}] at @s run title @s actionbar ["",{"text":"[■■■■----","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=61}] at @s run playsound block.anvil.use player @a ~ ~ ~ 1 0.99
execute as @a[tag=forging,scores={forgetime=76}] at @s run title @s actionbar ["",{"text":"[■■■■■---","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=91}] at @s run title @s actionbar ["",{"text":"[■■■■■■--","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=91}] at @s run playsound block.anvil.use player @a ~ ~ ~ 1 0.97
execute as @a[tag=forging,scores={forgetime=106}] at @s run title @s actionbar ["",{"text":"[■■■■■■■-","bold":true,"color":"gold"},{"text":"]","color":"gold"}]
execute as @a[tag=forging,scores={forgetime=120}] at @s run title @s actionbar ["",{"text":"[■■■■■■■■","bold":true,"color":"red"},{"text":"]","color":"red"}]
execute as @a[tag=forging,scores={forgetime=120}] at @s run playsound block.anvil.place player @a ~ ~ ~ 1
execute as @a[tag=forging,scores={forgetime=120}] at @s run playsound block.beacon.deactivate player @a ~ ~ ~ 1
execute as @a[tag=forging,scores={forgetime=120}] at @s run playsound block.beacon.deactivate player @a ~ ~ ~ 1
execute as @a[tag=forging,scores={forgetime=120}] at @s run playsound block.beacon.deactivate player @a ~ ~ ~ 1

execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:iron_ingot"}}] at @s run function battleforge:iron_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:gold_ingot"}}] at @s run function battleforge:gold_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:diamond"}}] at @s run function battleforge:diamond_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:netherite_ingot"}}] at @s run function battleforge:netherite_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:iron_block"}}] at @s run function battleforge:iron_block_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:gold_block"}}] at @s run function battleforge:gold_block_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:diamond_block"}}] at @s run function battleforge:diamond_block_forge
execute as @a[tag=forging,scores={forgetime=120},nbt={SelectedItem:{id:"minecraft:netherite_block"}}] at @s run function battleforge:netherite_block_forge

######## Power Countdown ######## 

scoreboard players remove @a[scores={iron_timer=0..3600}] iron_timer 1
scoreboard players remove @a[scores={gold_timer=0..3600}] gold_timer 1
scoreboard players remove @a[scores={diamond_timer=0..3600}] diamond_timer 1
scoreboard players remove @a[scores={netherite_timer=0..3600}] netherite_timer 1

execute as @a[scores={iron_timer=2400}] at @s run title @s actionbar ["",{"text":"Iron Imbuement runs out in 120 Seconds","bold":true,"color":"white"}]
execute as @a[scores={gold_timer=2400}] at @s run title @s actionbar ["",{"text":"Gold Imbuement runs out in 120 Seconds","bold":true,"color":"gold"}]
execute as @a[scores={diamond_timer=2400}] at @s run title @s actionbar ["",{"text":"Diamond Imbuement runs out in 120 Seconds","bold":true,"color":"aqua"}]
execute as @a[scores={netherite_timer=2400}] at @s run title @s actionbar ["",{"text":"Netherite Imbuement runs out in 120 Seconds","bold":true,"color":"dark_red"}]

execute as @a[scores={iron_timer=1200}] at @s run title @s actionbar ["",{"text":"Iron Imbuement runs out in 60 Seconds","bold":true,"color":"white"}]
execute as @a[scores={gold_timer=1200}] at @s run title @s actionbar ["",{"text":"Gold Imbuement runs out in 60 Seconds","bold":true,"color":"gold"}]
execute as @a[scores={diamond_timer=1200}] at @s run title @s actionbar ["",{"text":"Diamond Imbuement runs out in 60 Seconds","bold":true,"color":"aqua"}]
execute as @a[scores={netherite_timer=1200}] at @s run title @s actionbar ["",{"text":"Netherite Imbuement runs out in 60 Seconds","bold":true,"color":"dark_red"}]

execute as @a[scores={iron_timer=600}] at @s run title @s actionbar ["",{"text":"Iron Imbuement runs out in 30 Seconds","bold":true,"color":"white"}]
execute as @a[scores={gold_timer=600}] at @s run title @s actionbar ["",{"text":"Gold Imbuement runs out in 30 Seconds","bold":true,"color":"gold"}]
execute as @a[scores={diamond_timer=600}] at @s run title @s actionbar ["",{"text":"Diamond Imbuement runs out in 30 Seconds","bold":true,"color":"aqua"}]
execute as @a[scores={netherite_timer=600}] at @s run title @s actionbar ["",{"text":"Netherite Imbuement runs out in 30 Seconds","bold":true,"color":"dark_red"}]

######## Disable Power Effects ########

execute as @a[scores={iron_timer=0},tag=ironbuff1] at @s run playsound minecraft:block.beacon.deactivate player @s ~ ~ ~
execute as @a[scores={iron_timer=0},tag=ironbuff1] at @s run function battleforge:iron_forge_remove

execute as @a[scores={gold_timer=0},tag=goldbuff1] at @s run playsound minecraft:block.beacon.deactivate player @s ~ ~ ~
execute as @a[scores={gold_timer=0},tag=goldbuff1] at @s run function battleforge:gold_forge_remove

execute as @a[scores={diamond_timer=0},tag=diamondbuff1] at @s run playsound minecraft:block.beacon.deactivate player @s ~ ~ ~
execute as @a[scores={diamond_timer=0},tag=diamondbuff1] at @s run function battleforge:diamond_forge_remove

execute as @a[scores={netherite_timer=0},tag=netheritebuff1] at @s run playsound minecraft:block.beacon.deactivate player @s ~ ~ ~
execute as @a[scores={netherite_timer=0},tag=netheritebuff1] at @s run function battleforge:netherite_forge_remove

######## Disable Anvil Effects ########

execute as @a[tag=!forging] run attribute @s generic.movement_speed modifier remove 06eb4794-038d-4015-b38e-707ef176ba28
execute as @a[tag=!forging,scores={forgetime=1..}] run effect clear @s jump_boost
execute as @a[tag=!forging] run scoreboard players set @s forgetime 0
execute as @a[tag=forging] run tag @s remove forging

######## Clipboard ########