package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Battery;
import ee.taltech.iti0202.computerbuilder.components.CPU;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.components.ComputerCase;
import ee.taltech.iti0202.computerbuilder.components.GPU;
import ee.taltech.iti0202.computerbuilder.components.HDD;
import ee.taltech.iti0202.computerbuilder.components.Keyboard;
import ee.taltech.iti0202.computerbuilder.components.MotherBoard;
import ee.taltech.iti0202.computerbuilder.components.PSU;
import ee.taltech.iti0202.computerbuilder.components.RAM;
import ee.taltech.iti0202.computerbuilder.components.SSD;
import ee.taltech.iti0202.computerbuilder.components.Screen;
import ee.taltech.iti0202.computerbuilder.components.Touchpad;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Type;
import ee.taltech.iti0202.computerbuilder.computer.UseCase;
import ee.taltech.iti0202.computerbuilder.computerFactory.ComputerFactory;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.ComputerStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ComputerStoreTests {
    final int ten = 10;
    final int twelve = 12;
    final int fifty = 50;
    final int thirty = 30;
    final int fivehundred = 500;
    final int twenty = 20;
    final int forty = 40;
    final int hundred = 100;
    final int twohundred = 200;
    final int threehundred = 300;
    final int hundredfifty = 150;
    final int seventy = 70;
    final int sixty = 60;
    final int twohundredfifty = 250;
    final int threethousand = 3000;
    final int fourthousand = 4000;
    final int inventoryValue = 2720;

    Battery battery = new Battery("b1", Component.Type.BATTERY, fifty, "m1", thirty, fivehundred);
    ComputerCase computerCase = new ComputerCase("case1", Component.Type.CASE, twenty, "m2", twenty,
            fivehundred);
    CPU cpu = new CPU("cpu1", Component.Type.CPU, hundred, "m3", fifty, hundred);
    GPU gpu = new GPU("gpu1", Component.Type.GPU, hundred, "m4", thirty, fifty);
    HDD hdd = new HDD("hdd1", Component.Type.HDD, hundredfifty, "m5", forty, threehundred);
    Keyboard keyboard = new Keyboard("keyboard", Component.Type.KEYBOARD, hundred, "m6", fifty, ten);
    MotherBoard motherBoard = new MotherBoard("motherBoard", Component.Type.MOTHERBOARD, twohundred,
            "m7", seventy, thirty);
    PSU psu = new PSU("psu", Component.Type.PSU, twohundredfifty, "m8", fifty, fivehundred);
    RAM ram = new RAM("ram", Component.Type.RAM, seventy, "m9", sixty, thirty);
    Screen screen = new Screen("screen", Component.Type.SCREEN, fifty, "m10", fifty, threehundred);
    SSD ssd = new SSD("ssd", Component.Type.SSD, hundred, "m11", forty, forty);
    Touchpad touchpad = new Touchpad("touchpad", Component.Type.TOUCHPAD, seventy, "m12", fifty,
            threehundred);

    Database database1 = new Database();

    @Test
    void testComponentConstructor() {
        Assertions.assertEquals(battery.getId(), 0);
        Assertions.assertEquals(computerCase.getId(), 1);
        Assertions.assertEquals(computerCase.getName(), "case1");
        Assertions.assertEquals(cpu.getType(), Component.Type.CPU);
        Assertions.assertEquals(gpu.getPrice(), hundred);
        Assertions.assertEquals(hdd.getManufacturer(), "m5");
        Assertions.assertEquals(keyboard.getPerformancePoints(), fifty);
        Assertions.assertEquals(motherBoard.getPowerConsumption(), thirty);

    }
    @Test
    void testAssembleWorkPC() throws ProductAlreadyExistsException, NotEnoughMoneyException, ProductNotFoundException {

        ComputerStore computerStore1 = new ComputerStore("store1", BigDecimal.valueOf(fivehundred),
                BigDecimal.valueOf(2));

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

        computerStore1.setDatabase(database1);

        Assertions.assertEquals(computerStore1.getBalance(), BigDecimal.valueOf(fivehundred));

        ComputerFactory computerFactory = new ComputerFactory(computerStore1);

        computerStore1.setComputerFactory(computerFactory);

        Customer customer1 = new Customer("Kati", BigDecimal.valueOf(threethousand));

        Computer pc1 = computerStore1.purchaseComputer(customer1, UseCase.WORKSTATION, Type.PC);


        List<Computer> computers = List.of(pc1);

        Assertions.assertEquals(customer1.getComputers(), computers);
    }

    @Test
    void testAssembleWorkLaptop() throws ProductAlreadyExistsException,
            NotEnoughMoneyException, ProductNotFoundException {

        ComputerStore computerStore2 = new ComputerStore("store2", BigDecimal.valueOf(fivehundred),
                BigDecimal.valueOf(2));

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

        ComputerFactory computerFactory = new ComputerFactory(computerStore2);

        computerStore2.setComputerFactory(computerFactory);

        Customer customer2 = new Customer("Mari", BigDecimal.valueOf(threethousand));

        Computer laptop1 = computerStore2.purchaseComputer(customer2, UseCase.WORKSTATION, Type.LAPTOP);

        List<Computer> laptops = List.of(laptop1);


        Assertions.assertEquals(customer2.getComputers(), laptops);
    }

    @Test
    void testAssembleGamingPC() throws ProductAlreadyExistsException, NotEnoughMoneyException,
            ProductNotFoundException {

        ComputerStore computerStore3 = new ComputerStore("store3", BigDecimal.valueOf(fivehundred),
                BigDecimal.valueOf(2));

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

        computerStore3.setDatabase(database1);

        ComputerFactory computerFactory = new ComputerFactory(computerStore3);

        computerStore3.setComputerFactory(computerFactory);

        Customer customer3 = new Customer("Mati", BigDecimal.valueOf(fourthousand));

        Computer gamingComputer = computerStore3.purchaseComputer(customer3, UseCase.GAMING, Type.PC);

        List<Computer> gamingComputers = List.of(gamingComputer);

        Assertions.assertEquals(customer3.getComputers(), gamingComputers);
    }

    @Test
    void testPurchaseComponent() throws ProductAlreadyExistsException, NotEnoughMoneyException,
            ProductNotFoundException, OutOfStockException {

        ComputerStore computerStore4 = new ComputerStore("store4", BigDecimal.valueOf(fivehundred),
                BigDecimal.valueOf(2));

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

        computerStore4.setDatabase(database1);

        Assertions.assertEquals(computerStore4.getBalance(), BigDecimal.valueOf(fivehundred));

        ComputerFactory computerFactory = new ComputerFactory(computerStore4);

        computerStore4.setComputerFactory(computerFactory);

        Customer customer4 = new Customer("Kati", BigDecimal.valueOf(threethousand));

        Component component1 = computerStore4.purchaseComponent(battery.getId(), customer4);


        List<Component> components = List.of(component1);

        Assertions.assertEquals(customer4.getComponents(), components);
    }

    @Test
    void testSortingStoresDatabase() throws ProductAlreadyExistsException, ProductNotFoundException {
        Database database2 = new Database();

        ComputerStore computerStore3 = new ComputerStore("store3", BigDecimal.valueOf(fivehundred),
                BigDecimal.valueOf(2));

        database2.saveComponent(battery);
        database2.saveComponent(computerCase);
        database2.saveComponent(cpu);
        database2.saveComponent(gpu);
        database2.saveComponent(hdd);
        database2.saveComponent(keyboard);
        database2.saveComponent(motherBoard);
        database2.saveComponent(psu);
        database2.saveComponent(ram);
        database2.saveComponent(screen);
        database2.saveComponent(ssd);
        database2.saveComponent(touchpad);

        database2.increaseComponentStock(battery.getId(), 2);

        computerStore3.setDatabase(database2);

        Assertions.assertEquals(computerStore3.getAvailableComponents().size(), twelve);

        Assertions.assertEquals(computerStore3.getComponentsSortedByAmount().get(0), battery);

        Assertions.assertEquals(computerStore3.getComponentsSortedByName().get(0), battery);

        Assertions.assertEquals(computerStore3.getComponentsSortedByPrice().get(0), psu);

        Assertions.assertEquals(computerStore3.filterByType(Component.Type.BATTERY).size(), 1);

        Assertions.assertEquals(computerStore3.getInventoryValue().intValue(), inventoryValue);
    }

}
