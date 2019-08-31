package com.leet.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

class PrintInOrder {

	static AtomicInteger status = new AtomicInteger(0);

	public static void main(String args[]) throws InterruptedException {
		PrintInOrder foo = new PrintInOrder();

		new Thread(new Runnable() {
			public void run() {
				try {
					foo.third(new Runnable() {

						@Override
						public void run() {
							System.out.println("third");

						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					foo.second(new Runnable() {

						@Override
						public void run() {
							System.out.println("second");

						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					foo.first(new Runnable() {

						@Override
						public void run() {
							System.out.println("first");

						}
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	public PrintInOrder() {

	}

	public void first(Runnable printFirst) throws InterruptedException {
		// printFirst.run() outputs "first". Do not change or remove this line.
		while (status.get() != 0)
			continue;
		printFirst.run();
		status.getAndIncrement();
	}

	public void second(Runnable printSecond) throws InterruptedException {
		while (status.get() < 1) {
			// System.out.println("waiting in second");
			continue;
		}
		// printSecond.run() outputs "second". Do not change or remove this line.
		printSecond.run();
		status.getAndIncrement();
	}

	public void third(Runnable printThird) throws InterruptedException {
		while (status.get() < 2) {
			// System.out.println("waiting in third");
			continue;
		}
		// printThird.run() outputs "third". Do not change or remove this line.
		printThird.run();
		status.getAndSet(0);
	}
}