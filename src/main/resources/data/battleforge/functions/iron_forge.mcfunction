######## Removes Iron ########

clear @s minecraft:iron_ingot 1
scoreboard players set @s iron_timer 3600
scoreboard players set @s forgetime 0
effect clear @s jump_boost

######## 5th Tier Upgrade ########

attribute @s[tag=ironbuff4] generic.armor modifier add b1fe408e-5f26-43f8-9a34-b7a42c14c6ee "ironarmorbuff5" 4 add
attribute @s[tag=ironbuff4] generic.armor_toughness modifier add 71fe049b-6b37-4a01-a0a5-e475277ac3b6 "ironarmortoughnessbuff5" 2 add
tag @s[tag=ironbuff4] add ironbuff5

######## 4th Tier Upgrade ########

attribute @s[tag=ironbuff3] generic.armor modifier add 1e6756c9-5da0-4801-a6b4-1a2b112f9c25 "ironarmorbuff4" 4 add
attribute @s[tag=ironbuff3] generic.armor_toughness modifier add d41c210a-4ea7-4759-b070-674b6116f61e "ironarmortoughnessbuff4" 2 add
tag @s[tag=ironbuff3] add ironbuff4

######## 3rd Tier Upgrade ########

attribute @s[tag=ironbuff2] generic.armor modifier add c4c442c6-9007-4476-a12f-e6e77c631a89 "ironarmorbuff3" 4 add
attribute @s[tag=ironbuff2] generic.armor_toughness modifier add acfa0a49-c364-47ed-bfd1-c1ebc9d90098 "ironarmortoughnessbuff3" 2 add
attribute @s[tag=ironbuff2] generic.movement_speed modifier add 447b0fc1-e92a-4f31-94a2-828ecfe38494 "ironspeedbuff3" -0.02 add
tag @s[tag=ironbuff2] add ironbuff3

######## 2nd Tier Upgrade ########

attribute @s[tag=ironbuff1] generic.armor modifier add a72a113b-f0ab-41a8-8216-f65c6d0e7901 "ironarmorbuff2" 4 add
attribute @s[tag=ironbuff1] generic.armor_toughness modifier add 33b5dbbe-db67-4172-b2a6-7b23055bdf76 "ironarmortoughnessbuff2" 2 add
attribute @s[tag=ironbuff1] generic.movement_speed modifier add 1a6ed0de-81de-42e7-ae79-783fab229b89 "ironspeedbuff2" -0.02 add
tag @s[tag=ironbuff1] add ironbuff2

######## 1st Tier Upgrade ########

attribute @s generic.armor modifier add 3d92920d-545a-46b2-bae4-e6513a06a09f "ironarmorbuff1" 4 add
attribute @s generic.armor_toughness modifier add 21399685-9f57-4887-9fc8-be267cc99e3f "ironarmortoughnessbuff1" 2 add
attribute @s generic.movement_speed modifier add 9b0ba1a6-c82e-4140-a646-a4cca0aaa6a4 "ironspeedbuff1" -0.01 add
tag @s add ironbuff1