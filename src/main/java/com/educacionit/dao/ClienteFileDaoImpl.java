package com.educacionit.dao;

import com.educacionit.model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteFileDaoImpl implements ClienteDao {

    private static final String SEPARADOR = ",";
    private static final String FINAL_DE_LINEA = "\n";
    private static final String FILE_PATH = "src/main/resources/";
    private static final String FILE_NAME = "clientes.csv";
    private static final String FILE_NAME_TEMP = "clientes-tmp.csv";

    public void insert(Cliente cliente) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH + FILE_NAME, true));

            writer.append(String.valueOf(System.currentTimeMillis()));
            writer.append(SEPARADOR);
            writer.append(cliente.getNombre().trim());
            writer.append(SEPARADOR);
            writer.append(cliente.getApellido());
            writer.append(SEPARADOR);
            writer.append(cliente.getEmail());
            writer.append(FINAL_DE_LINEA);

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Cliente cliente) {
        File file = new File(FILE_PATH + FILE_NAME);

        BufferedReader reader = null;
        StringBuffer inputBuffer = new StringBuffer();

        String line;

        String clienteViejo = "";

        try {
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                if (line.contains(String.valueOf(cliente.getId()))) {
                    clienteViejo = line;
                }

                inputBuffer.append(line);
                inputBuffer.append(FINAL_DE_LINEA);
            }

            reader.close();

            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace(clienteViejo, cliente.toString());

            FileWriter writer = new FileWriter(FILE_PATH + FILE_NAME);

            writer.append(inputStr);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cliente getById(Long id) {
        File file = new File(FILE_PATH + FILE_NAME);

        BufferedReader reader = null;
        String line;

        Cliente cliente = new Cliente();

        try {
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {

                if (line.contains(String.valueOf(id))) {
                    List<String> propiedadesCliente = Arrays.asList(line.split(SEPARADOR));

                    cliente.setId(id);
                    cliente.setNombre(propiedadesCliente.get(1));
                    cliente.setApellido(propiedadesCliente.get(2));
                    cliente.setEmail(propiedadesCliente.get(3));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return cliente;
    }

    public List<Cliente> getClientes() {
        File file = new File(FILE_PATH + FILE_NAME);
        BufferedReader reader = null;
        String line;
        List<Cliente> clientes = new ArrayList();

        try {

            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                List<String> propiedadesCliente = Arrays.asList(line.split(SEPARADOR));

                Cliente cliente = new Cliente();

                long id = Long.parseLong(propiedadesCliente.get(0));

                cliente.setId(id);
                cliente.setNombre(propiedadesCliente.get(1));
                cliente.setApellido(propiedadesCliente.get(2));
                cliente.setEmail(propiedadesCliente.get(3));

                clientes.add(cliente);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clientes;
    }

    public void delete(Long id) {
        File inputFile = new File(FILE_PATH + FILE_NAME);
        File tempFile = new File(FILE_PATH + FILE_NAME_TEMP);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile, true));

            String lineaARemover = String.valueOf(id);
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(lineaARemover)) continue;

                writer.append(line);
                writer.append(FINAL_DE_LINEA);
            }

            writer.flush();
            tempFile.renameTo(inputFile);
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}