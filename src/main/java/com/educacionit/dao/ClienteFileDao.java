package com.educacionit.dao;

import com.educacionit.model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteFileDao implements ClienteDao {

    private static final String SEPARATOR = ",";
    private static final String END_OF_LINE = "\n";
    private static final String PATH_TO_FILE = "src/main/resources/";
    private static final String FILE_NAME = "clientes.csv";
    private static final String FILE_NAME_TEMP = "clientes-temp.csv";

    @Override
    public void insert(Cliente cliente) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(PATH_TO_FILE + FILE_NAME, true));
            write(writer, cliente, System.currentTimeMillis());
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

    @Override
    public void update(Cliente cliente) {
        File file = new File(PATH_TO_FILE + FILE_NAME);
        BufferedReader bufferedReader = null;
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        String oldClientAsString = "";

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(String.valueOf(cliente.getId()))) {
                    oldClientAsString = line;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            bufferedReader.close();

            String inputStr = inputBuffer.toString();

            inputStr = inputStr.replace(oldClientAsString, cliente.toString());

            FileWriter writer = new FileWriter(PATH_TO_FILE + FILE_NAME);

            writer.append(inputStr);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Cliente getById(Long id) {
        File file = new File(PATH_TO_FILE + FILE_NAME);
        BufferedReader bufferedReader = null;
        String line = "";
        Cliente cliente = new Cliente();

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            while ((line = bufferedReader.readLine()) != null) {

                if (line.contains(String.valueOf(id))) {
                    List<String> clientProperties = Arrays.asList(line.split(SEPARATOR));

                    cliente.setId(id);
                    cliente.setNombre(clientProperties.get(1));
                    cliente.setApellido(clientProperties.get(2));
                    cliente.setEmail(clientProperties.get(3));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return cliente;
    }

    @Override
    public List<Cliente> getClientes() {
        File file = new File(PATH_TO_FILE + FILE_NAME);
        BufferedReader bufferedReader = null;
        String line;
        List<Cliente> clientes = new ArrayList<>();

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            while ((line = bufferedReader.readLine()) != null) {
                List<String> clientProperties = Arrays.asList(line.split(SEPARATOR));

                Cliente cliente = new Cliente();

                Long id = Long.valueOf(clientProperties.get(0));

                cliente.setId(id);
                cliente.setNombre(clientProperties.get(1));
                cliente.setApellido(clientProperties.get(2));
                cliente.setEmail(clientProperties.get(3));


                clientes.add(cliente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return clientes;
    }

    @Override
    public void delete(Long id) {
        File inputFile = new File(PATH_TO_FILE + FILE_NAME);
        File tempFile = new File(PATH_TO_FILE + FILE_NAME_TEMP);

        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile, true));

            String lineToRemove = String.valueOf(id);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToRemove)) continue;
                writer.append(currentLine);
                writer.append(END_OF_LINE);
            }

            writer.flush();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
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

    private void write(BufferedWriter writer, Cliente cliente, Long id) throws IOException {
        writer.append(String.valueOf(id));
        writer.append(SEPARATOR);
        writer.append(cliente.getNombre().trim());
        writer.append(SEPARATOR);
        writer.append(cliente.getApellido());
        writer.append(SEPARATOR);
        writer.append(cliente.getEmail());
        writer.append(END_OF_LINE);
        writer.flush();
    }
}