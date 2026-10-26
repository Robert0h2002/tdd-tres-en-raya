# Script para la prueba de mutantes de la presentacion.
# Uso (desde la raiz del repo): python docs/mutantes.py .
import subprocess,re,shutil,sys,os
os.chdir(sys.argv[1])
P='src/main/java/com/ucb/tdd/TresEnRaya.java'
orig=open(P, newline='').read()
M=[
("M0 Original", None, None),
("M1 Limite superior mal (> 4)", "valor > TAMANIO", "valor > TAMANIO + 1"),
("M2 Limite inferior mal (< 0)", "valor < 1", "valor < 0"),
("M3 No valida lugar ocupado", "if (tablero[x - 1][y - 1] != VACIO) {", "if (false) {"),
("M4 Mensaje de excepcion cambiado", '" esta fuera del tablero"', '" invalido"'),
("M5 Sin diagonal secundaria", "|| esLinea(tablero[0][2], tablero[1][1], tablero[2][0]);", ";"),
("M6 Solo revisa la primera fila/columna", "for (int i = 0; i < TAMANIO; i++) {\n            if (esLinea", "for (int i = 0; i < 1; i++) {\n            if (esLinea"),
("M7 Sin lineas verticales", "|| esLinea(tablero[i][0], tablero[i][1], tablero[i][2])) {", ") {"),
("M8 Turno avanza aunque la jugada sea invalida", "verificarEje(x, \"X\");", "ultimoJugador = siguienteJugador();\n        verificarEje(x, \"X\");"),
("M9 Mensaje de ganador fijo en X", 'return ultimoJugador + " es el ganador";', 'return "X es el ganador";'),
("M10 Mensaje sin ganador cambiado", 'return "No hay ganador";', 'return "Sin ganador";'),
]
for name,a,b in M:
    src=orig
    if a:
        assert a in src, name
        src=src.replace(a,b,1)
    open(P,'w',newline='').write(src)
    out=subprocess.run('mvn -B -q clean test -Dsurefire.printSummary=true',shell=True,capture_output=True,text=True).stdout
    res={}
    for cls in ['TresEnRayaTest','TresEnRayaIATest']:
        m=re.search(r'Tests run: (\d+), Failures: (\d+), Errors: (\d+).*?in com\.ucb\.tdd\.'+cls+r'\b',out)
        res[cls]= 'n/a' if not m else f"{int(m.group(2))+int(m.group(3))}/{m.group(1)} fallan"
    print(f"{name:50s} propias: {res['TresEnRayaTest']:15s} IA: {res['TresEnRayaIATest']}",flush=True)
open(P,'w',newline='').write(orig)
