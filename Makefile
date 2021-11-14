all: run

clean:
	rm -f out/Main.jar out/FermatFactorization.jar

out/Main.jar: out/parcs.jar src/Main.java src/Result.java
	@javac -cp out/parcs.jar src/Main.java src/Result.java
	@jar cf out/Main.jar -C src Main.class -C src Result.class
	@rm -f src/Main.class src/Result.class

out/FermatFactorization.jar: out/parcs.jar src/FermatFactorization.java src/Result.java
	@javac -cp out/parcs.jar src/FermatFactorization.java src/Result.java
	@jar cf out/FermatFactorization.jar -C src FermatFactorization.class -C src Result.class
	@rm -f src/FermatFactorization.class src/Result.class

build: out/Main.jar out/FermatFactorization.jar

run: out/Main.jar out/FermatFactorization.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main