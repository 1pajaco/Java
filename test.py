import matplotlib.pyplot as plt
import numpy as np

# Definirajmo točke trapeza
točke = np.array([[0, 0], [10, 0], [20, 10], [20, 20]])

# Narišimo trapez
plt.fill(točke[:, 0], točke[:, 1], 'blue', alpha=0.5)

# Nastavimo osi in oznake
plt.xlabel('Čas dostave ($T_D$) [min]')
plt.ylabel('Čas Blaževega prihoda ($T_B$) [min]')
plt.title('Trapez za dogodek $A \cap B$')
plt.grid(True)
plt.xlim(0, 25)
plt.ylim(0, 25)

# Prikažemo graf
plt.show()