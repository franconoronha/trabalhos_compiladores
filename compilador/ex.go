package main
import ("fmt")
var _ = fmt.Println

func main() {
var teste_a float64
teste_a = 20
if true {
teste_a = quadrado(teste_a)
}
for {
teste_a = teste_a - 10
if !(teste_a > 100) {break}
}
fmt.Println(teste_a)
}

func quadrado (x float64) float64 {
return x * x
}

