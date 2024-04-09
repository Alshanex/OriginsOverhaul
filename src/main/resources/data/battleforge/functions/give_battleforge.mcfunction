recipe take @s battleforge:battleforge

advancement revoke @s only battleforge:battleforge_advancement

clear @s minecraft:knowledge_book

give @s[tag=piglin] carrot_on_a_stick{display:{Name:'{"text":"Battle Forge","color":"red","bold":true,"italic":false}'},HideFlags:4,Unbreakable:1b,CustomModelData:100345,battleforge:1b,AttributeModifiers:[{AttributeName:"generic.movement_speed",Name:"generic.movement_speed",Amount:-0.4,Operation:2,UUID:[I;-1299791175,-1801499497,-1755487971,1715842049],Slot:"offhand"},{AttributeName:"generic.attack_speed",Name:"generic.attack_speed",Amount:-0.4,Operation:2,UUID:[I;1215577177,1513965517,-1391408255,1889215703],Slot:"offhand"}]} 1
give @s[tag=!piglin] gold_block 3
give @s[tag=!piglin] gold_ingot 3
give @s[tag=!piglin] netherite_block
playsound block.anvil.use player @s[tag=!piglin] ~ ~ ~
title @s[tag=!piglin] actionbar {"text":"The construction of this craft evades you...","bold":true,"color":"dark_red"}