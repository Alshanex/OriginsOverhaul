######## Removes Netherite ########

clear @s minecraft:netherite_block 1
scoreboard players set @s netherite_timer 3600
scoreboard players set @s forgetime 0
effect clear @s jump_boost

######## 5th Tier Upgrade ########

attribute @s generic.attack_damage modifier add dfcf388c-a84c-4370-8b10-c7eba2c94dec "netheritebuff5" 1 multiply_base
tag @s add netheritebuff5

######## 4th Tier Upgrade ########

attribute @s generic.attack_damage modifier add 392aaf8c-9887-4e16-a5c2-c0051c200a19 "netheritebuff4" 0.5 multiply_base
attribute @s generic.attack_speed modifier add e4c24c1c-bf44-49d5-a5d3-fca5b7f251a6 "netheritespeedbuff4" -0.1 multiply_base
tag @s add netheritebuff4

######## 3rd Tier Upgrade ########

attribute @s generic.attack_damage modifier add e9b7a21d-6227-4d9e-8aba-e87a53a60523 "netheritebuff3" 0.5 multiply_base
attribute @s generic.attack_speed modifier add 0ed3f1f0-1bac-43c3-b1e3-f57607ddb6f7 "netheritespeedbuff3" -0.1 multiply_base
tag @s add netheritebuff3

######## 2nd Tier Upgrade ########

attribute @s generic.attack_damage modifier add 15597525-905a-481e-be57-d8ca155c6bc7 "netheritebuff2" 0.5 multiply_base
attribute @s generic.attack_speed modifier add a909637a-8ccd-4696-9161-d76dd0390ed6 "netheritespeedbuff2" -0.1 multiply_base
tag @s add netheritebuff2

######## 1st Tier Upgrade ########

attribute @s generic.attack_damage modifier add 6ad99c24-d1ec-4f37-b424-2e68a0d82990 "netheritebuff1" 0.5 multiply_base
attribute @s generic.attack_speed modifier add 907db45f-f81f-4013-b658-c8335c4effdc "netheritespeedbuff1" -0.1 multiply_base
tag @s add netheritebuff1