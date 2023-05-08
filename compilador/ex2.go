package main
import ("fmt")
var _ = fmt.Println

func main() {
var x int
x = 12
teste(x, 10, 30)
if true {
x = x / 2 - x / 1000
}
}

func teste (x int, y int, z int)  {
//var y int
for teste2(x){
fmt.Println(x)
x = x * 2
}
}

func teste2 (x int) bool {
return x > 1 && x < 1000
}

