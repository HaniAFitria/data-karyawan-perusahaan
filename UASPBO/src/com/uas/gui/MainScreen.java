package com.uas.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListKaryawan;
    private JTextField txtFilter;
    private JButton btnFilter;
    private JTextField txtNama;
    private JTextField txtNik;
    private JTextField txtGaji;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel lblNama;
    private JLabel lblNik;
    private JLabel lblGaji;
    List<Karyawan> arrayListKaryawan = new ArrayList<>();

    DefaultListModel defaultListModel = new DefaultListModel();
    class Karyawan {
        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNik() {
            return nik;
        }

        public void setNik(String nik) {
            this.nik = nik;
        }

        public String getGaji() {
            return gaji;
        }

        public void setGaji(String gaji) {
            this.gaji = gaji;
        }

        public String nama;
        public String nik;
        public String gaji;
    }

    public MainScreen(){
        super("Data Karyawan");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtNama.getText();
                String nim = txtNik.getText();
                String gaji = txtGaji.getText();

                Karyawan karyawan = new Karyawan();

                karyawan.setNama(nama);
                karyawan.setNik(nim);
                karyawan.setGaji(gaji);

                arrayListKaryawan.add(karyawan);
                clearForm();


                fromListKaryawanToListModel();


            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();

            }
        });
        jListKaryawan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListKaryawan.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListKaryawan.getSelectedValue().toString();

                for (int i = 0; i < arrayListKaryawan.size(); i++) {
                    if(arrayListKaryawan.get(i).getNama().equals(nama)){
                        Karyawan karyawan = arrayListKaryawan.get(i);
                        txtNama.setText(karyawan.getNama());
                        txtNik.setText(karyawan.getNik());
                        txtGaji.setText(karyawan.getGaji());
                        break;
                    }

                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListKaryawan.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListKaryawan.getSelectedValue().toString();

                for (int i = 0; i < arrayListKaryawan.size() ; i++) {
                    if (arrayListKaryawan.get(i).getNama().equals(nama)){
                        arrayListKaryawan.remove(i);
                    }

                }

                clearForm();
                fromListKaryawanToListModel();
            }
        });
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyWord = txtFilter.getText();

                List<String> filter = new ArrayList<>();

                for (int i = 0; i < arrayListKaryawan.size(); i++) {
                    if (arrayListKaryawan.get(i).getNama().contains(keyWord)){
                        filter.add(arrayListKaryawan.get(i).getNama());
                    }

                }

                refreshListModel(filter);
            }
        });
    }

    public void clearForm(){
        txtNama.setText("");
        txtNik.setText("");
        txtGaji.setText("");
    }

    public void fromListKaryawanToListModel(){
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayListKaryawan.size(); i++) {
            listNamaMahasiswa.add(arrayListKaryawan.get(i).getNama());
        }
        refreshListModel(listNamaMahasiswa);

        }


    public void refreshListModel(List<String> list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListKaryawan.setModel(defaultListModel);
    }





    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
