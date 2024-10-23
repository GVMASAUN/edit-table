import { Injectable } from '@angular/core';
import { Hero } from './hero';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HeroService {
  heroes: Hero[] = [
    { id: 12, name: 'Dr. Nice' },
    { id: 13, name: 'Bombasto' },
    { id: 14, name: 'Celeritas' },
    { id: 15, name: 'Magneta' },
    { id: 16, name: 'RubberMan' },
    { id: 17, name: 'Dynama' },
    { id: 18, name: 'Dr. IQ' },
    { id: 19, name: 'Magma' },
    { id: 20, name: 'Tornado' }
  ]
  constructor(private messageService: MessageService) { }
  getHeroes(): Observable<Hero[]> {
    const HEROES = of(this.heroes);
    return HEROES;
  }

  getHero(id: number): Observable<Hero> {
    const hero = this.heroes.find(hero => hero.id === id)!;
    this.messageService.addMessage(`Fetched Heroe id: ${id}`);
    return of(hero);
  }
}