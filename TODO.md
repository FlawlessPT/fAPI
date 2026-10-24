# TODO — fAPI

Pendências conhecidas deste plugin (15/10/2026). Riscar quando feito.

## Código
- [ ] `FInventory`: criar o `FItemStack` (comentário `TODO` em `inventory/FInventory.java`).

## Ideias
- [ ] Contrato de BD: um auxiliar `transactionAsync` para correr várias instruções numa só transação, sem cada plugin repetir o `setAutoCommit(false)` / `commit()` / `rollback()`.
- [ ] Contrato de economia (por exemplo `EconomyApi`) no fAPI, para plugins como o fShop usarem a economia sem depender do fEconomy.

## Qualidade
- [ ] Mais testes: hoje só o `DatabasesTest` existe (ActionBar, Title, Sound, Inventory e BCrypt não têm).

## Migração Paper 1.21
- [ ] `FItemStackTest` apagado: só testava `setDurability`/`setMaterialData`, removidos do `FItemStack` (a cor passa a estar no `Material`).
