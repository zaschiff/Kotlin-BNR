class Barrel<out T>(val item: T)

fun main(args: Array<String>) {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("a generic-looking fedora", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(50))
    lootBarrel = fedoraBarrel
    val mtFedora: Fedora = lootBarrel.item
}
