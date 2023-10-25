fun main(){val(r,c)=readln().split(" ").map{it.toInt()}
val m=Array(r){readln().toCharArray()}
fun d(y:Int,x:Int):Boolean=if(x==c)true
else if(y<0||y>=r||m[y][x]>'w')false
else{m[y][x]='x'
(-1..1).any{d(y+it,x+1)}}
print((0..r).count{d(it,0)})}