class Interpretador {
    Interpretador() {}

	float executa (ArvoreSintatica arv) {
        if (arv instanceof Mult)
            return (executa(((Mult) arv).arg1) * executa(((Mult) arv).arg2));

        if (arv instanceof Soma)
            return (executa(((Soma) arv).arg1) + executa(((Soma) arv).arg2));

        if (arv instanceof Sub)
            return (executa(((Sub) arv).arg1) - executa(((Sub) arv).arg2));
            
        if (arv instanceof Div)
            return (executa(((Div) arv).arg1) / executa(((Div) arv).arg2));

        if (arv instanceof Num)
            return (((Num) arv).num);

        return 0;
	}
}