package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.*;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.PC;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.computerFactory.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.ComputerStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ComputerStoreTests {
    Battery battery = new Battery("b1", Component.Type.BATTERY, 50, "m1", 30, 500);
    ComputerCase computerCase = new ComputerCase("case1", Component.Type.CASE, 20, "m2", 20, 500);
    CPU cpu = new CPU("cpu1", Component.Type.CPU, 100, "m3", 50, 100);
    GPU gpu = new GPU("gpu1", Component.Type.GPU, 100, "m4", 30, 50);
    HDD hdd = new HDD("hdd1", Component.Type.HDD, 150, "m5", 40, 300);
    Keyboard keyboard = new Keyboard("keyboard", Component.Type.KEYBOARD, 100, "m6", 50, 300);
    MotherBoard motherBoard = new MotherBoard("motherBoard", Component.Type.MOTHERBOARD, 200, "m7", 70, 30);
    PSU psu = new PSU("psu", Component.Type.PSU, 250, "m8", 50, 500);
    RAM ram = new RAM("RAM", Component.Type.RAM, 70, "m9", 60, 30);
    Screen screen = new Screen("screen", Component.Type.SCREEN, 50, "m10", 50, 300);
    SSD ssd = new SSD("ssd", Component.Type.SSD, 100, "m11", 40, 40);
    Touchpad touchpad = new Touchpad("touchpad", Component.Type.TOUCHPAD, 70, "m12", 50, 300);

    Customer customer1 = new Customer("Mari", BigDecimal.valueOf(20000));
    @Test
    void testComponentConstructor() {
        Assertions.assertEquals(battery.getId(), 0);
        Assertions.assertEquals(computerCase.getName(), "case1");
        Assertions.assertEquals(cpu.getType(), Component.Type.CPU);
        Assertions.assertEquals(gpu.getPrice(), 100);
        Assertions.assertEquals(hdd.getManufacturer(), "m5");
        Assertions.assertEquals(keyboard.getPerformancePoints(), 50);
        Assertions.assertEquals(motherBoard.getPowerConsumption(), 30);

    }
    @Test
    void testComputerFactory() throws ProductAlreadyExistsException, NotEnoughMoneyException, ProductNotFoundException {
        Database database1 = new Database();
        ComputerStore computerStore2 = new ComputerStore("store2", BigDecimal.valueOf(500), BigDecimal.valueOf(2));
        database1.saveComponent(battery);
        database1.saveComponent(computerCase);
        database1.saveComponent(cpu);
        database1.saveComponent(gpu);
        database1.saveComponent(hdd);
        database1.saveComponent(keyboard);
        database1.saveComponent(motherBoard);
        database1.saveComponent(psu);
        database1.saveComponent(ram);
        database1.saveComponent(screen);
        database1.saveComponent(ssd);
        database1.saveComponent(touchpad);

        computerStore2.setDatabase(database1);

        Assertions.assertEquals(computerStore2.getBalance(), BigDecimal.valueOf(500));

        ComputerFactory computerFactory = new ComputerFactory(computerStore2);

        computerStore2.setComputerFactory(computerFactory);

        Customer customer2 = new Customer("Kati", BigDecimal.valueOf(3000));

        Computer pc1 = computerStore2.purchaseComputer(customer2, UseCase.WORKSTATION, Type.PC);

        List<Computer> computers = List.of(pc1);
        Assertions.assertEquals(customer1.getComputers(), computers);
    }

}
