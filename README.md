# ProjetoPooClinicaVeterinaria
Repository of an object-oriented programming project. A java registration system was developed for a veterinary clinic integrated with a database.
# Objetivo:
O trabalho desenvolvido pela equipe terá como objetivo implementar um Sistema de cadastro para uma Clínica Veterinária, permitindo o registro do fluxo de atendimento do paciente e operações realizadas para seu tratamento. Entre as atividades principais estão: realização de consultas, exames químicos e de imagem (hemograma, Raio-X, Ultrassom), cadastro de paciente, tutor e funcionários. Além disso também deverão ser registrados os prontuários de pacientes internados.
Este trabalho comtemplará as atividades principais para funcionamento da empresa, no entanto a fim de manter a complexidade do sistema simples para o objetivo da matéria , o sistema não irá armazenar as informações de atividades administrativas, e de relacionamento com o cliente.
Cenário:
Temos que o banco de dados irá se estabelecer sob a perspectiva da clínica veterinária Pet Way Care, no qual será administrado serviços de atendimento a tutores e pets. A clínica, de tamanho médio e de unidade única, desempenha funções que visam a saúde dos pacientes e um ambiente de trabalho organizado, evitando ao máximo o despreparo ou atendimentos ineficientes. Dessa forma, é fundamental que o banco de dados seja objetivo e de fácil interpretação, em virtude de minimizar possíveis consequências a um tutor ou paciente caso ocorra um equívoco no banco de dados.
Além disso, podemos relevar que a clínica é ativa 24 horas por dia. Logo, devemos ter em mente a troca de turno entre os funcionários e a administração de medicamentos para pacientes internados ou emergências. Consequentemente, devemos relevar a dinâmica de funcionamento do petshop, de modo que a divisão de horários entre atendimento, limpeza, esterilização e cirurgias sempre ocorrerão com a clínica em funcionamento. Assim, sabemos que a clínica veterinária apresenta alta competência e complexidade ao exercer sua função, sendo fundamental um equilíbrio entre os agentes que se relacionam a clínica.
# Escopo:
O escopo do trabalho irá abranger as atividades de uma Clínica Veterinária de tamanho médio e uma única unidade. A empresa possui cerca de 20 funcionários e atende até 30 clientes por dia em diferentes atividades como consultas, exames, vacinas e cirurgias. Nesse cenário os pacientes são denominados “Pets” e cada pet possui um tutor responsável cadastrado, um tutor pode ter mais de um pet, mas um pet só pode ter um tutor responsável.
Ademais, a Clínica possui 6 veterinários que desempenham diferentes atividades como: atendimento em consultas, cirurgias, realização de exames e monitoramento e
internação. Logo um paciente pode ser atendido por vários veterinários que também atendem a muitos pacientes. Os demais colaboradores incluem: recepcionistas, auxiliares veterinários e auxiliares de limpeza.
O fluxo de um paciente a partir do primeiro contato parte do atendimento pelo recepcionista onde será feito o cadastro do pet e do tutor e encaminhamento para consulta, em seguida serão realizados exames adicionais para finalizar diagnóstico e/ou liberação do paciente com prescrição para tratamento em casa. Outro cenário possível é a realização de internação para tratamento intensivo ou preparação para procedimento cirúrgico, durante o período de internação podem ser feitos exames adicionais e são cadastrados 2 prontuários diários que são anexados ao cadastro do pet.
Ao final do fluxo o paciente é liberado novamente passando pelo recepcionista que faz a cobrança dos valores dos procedimentos feitos.
# Não Escopo:
O projeto não irá abordar atividades administrativas ou de fluxo de mercadorias e estoque da Clínica Veterinária, logo operações relacionadas a fluxo de caixa, pagamento de contas, fechamento de conta de clientes, entrada de produtos e vendas não terão suas informações armazenadas. O Sistema deverá focar exclusivamente no percurso do paciente e nas atividades que o envolvem diretamente a fim de possibilitar um atendimento rápido e eficiente.
# Funcionalidades:
O sistema deve possibilitar o armazenamento do cadastro do tutor e do pet, atentando-se para o fato de um tutor poder ter vários pets, mas um pet pode ter somente um tutor. Deve ser armazenado as informações de uma consulta como veterinário responsável, pet e tutor, horário, data e valor. Um pet pode ser atendido por vários veterinários e um veterinário pode atender a vários pets.
O sistema deve oferecer funções como:
• consultar o cadastro de um tutor e/ou um pet além de alterar informações cadastradas.
• Cadastro de exames feitos por um pet.
• Cadastro de Cirurgias realizadas por um pet.
• Cadastro de uma internação e de prontuários a ela relacionada.
• Cadastro de Veterinários e alteração de dados dos veterinários.
• Consulta de veterinários por nome, cpf ou especialidades.
• Consulta de pets atendidos por um veterinário.
• Consulta de tutores com conta em aberto.
• Cadastro de funcionários e consulta de dados.
# Tecnologias:
Para o desenvolvimento do projeto foi utilizado o ambiente do Intellij IDEA, maven para gerenciamento do projeto, JPA e Hibernate para persistência dos dados cadastrados em um banco de dados MySQL. Além disso para o desenvilvimento da interface gráfica foi utilizada a biblioteca Swing do Java e as ferramentas de GUI do próprio IntelliJ.
