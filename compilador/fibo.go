package main
import ("fmt")
var _ = fmt.Println

func main() {
var x int
x = 10
fmt.Println(fibonacci(x))
}

func fibonacci (n int) int {
if n == 1 || n == 2 {
return n
}
return fibonacci(n - 1) + fibonacci(n - 2)
}

