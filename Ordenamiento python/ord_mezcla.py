arr = [42, 7, 89, 15, 63, 2, 31, 55, 98, 12]

def ord_mezla(arr):
   
    if len(arr) == 1 or len(arr) == 0:
        return arr

    mitad = len(arr) // 2
    
    m_izquierda = ord_mezla(arr[:mitad])
    m_derecha = ord_mezla(arr[mitad:])

    return mezclar(m_izquierda, m_derecha)

def mezclar(izq, der):
    res = []
    ind_izq = 0
    ind_der = 0
    
    while ind_izq < len(izq) and ind_der < len(der):
        if izq[ind_izq] < der[ind_der]:
            res.append(izq[ind_izq])
            ind_izq += 1
        else:
            res.append(der[ind_der])
            ind_der += 1
            
    res.extend(izq[ind_izq:])
    res.extend(der[ind_der:])
    return res

print("arreglo original:", arr)
final_arr = ord_mezla(arr)
print("arreglo ordenado:", final_arr)